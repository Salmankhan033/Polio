package com.taif.polio.Common.SigninSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.taif.polio.HelperClasses.ChildData;
import com.taif.polio.NavigationDrawer.NavigationDrawerActivity;
import com.taif.polio.R;

public class AddDataActivity extends AppCompatActivity {
    TextInputLayout name, fatherName,motherName,mobileNo, address,age,dateOfBirth;
    ImageView backBtn;
    Button saveDataBtn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        name=findViewById(R.id.add_fullName);
        fatherName=findViewById(R.id.add_father);
        motherName=findViewById(R.id.add_mother);
        mobileNo=findViewById(R.id.add_phone);
        address=findViewById(R.id.add_address);
        age=findViewById(R.id.add_age);
        dateOfBirth=findViewById(R.id.add_date);
        backBtn=findViewById(R.id.addback);
        saveDataBtn=findViewById(R.id.saveBtn);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Waiting...");
        progressDialog.setMessage("Waiting PLease...");


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);

                //Add Transition
                Pair[] pairs = new Pair[1];

                pairs[0] = new Pair<View, String>(backBtn, "transition_back_arrow_btn");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(AddDataActivity.this, pairs);
                    startActivity(intent, options.toBundle());

                } else {
                    startActivity(intent);
                }
            }
        });


       saveDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Call the validate functions
                if (!validateFullName() | !validateAddress() | ! validateAge() | !validateDateB() | !validateFatherName() | !validateMobile() | !validateMotherName()){
                    return;
                }

                progressDialog.show();


                //Get all field data from their Text boxes
                String fullName = name.getEditText().getText().toString();
                String f_name = fatherName.getEditText().getText().toString();
                String m_name = motherName.getEditText().getText().toString();
               String ages = age.getEditText().getText().toString();
                String date_bith = dateOfBirth.getEditText().getText().toString();
                String Address = address.getEditText().getText().toString();
                String PhoneNo = mobileNo.getEditText().getText().toString();
                ChildData childData=new ChildData(fullName,f_name,m_name,date_bith,ages,Address,PhoneNo,"");
                databaseReference.child("Children").child(ages).push().setValue(childData).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        progressDialog.dismiss();
                          Toast.makeText(getApplicationContext(),"Data Uploaded Succesfully",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(getApplicationContext(),NavigationDrawerActivity.class);
                        startActivity(intent);
                    }
                });

//                if (ages<=6){
//                    Toast.makeText(getApplicationContext(),"Age is "+ages,Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(getApplicationContext(),"Age is greater then 6 "+ages,Toast.LENGTH_LONG).show();
//                }








            }
        });


    }




    //Validation Input Fields
    private boolean validateFullName() {
        String fullName_value = name.getEditText().getText().toString().trim();

        if (fullName_value.isEmpty()) {
            name.setError("Field can not be empty");
            return false;
        } else {
            name.setError(null);
            name.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateFatherName() {
        String fullName_value = fatherName.getEditText().getText().toString().trim();

        if (fullName_value.isEmpty()) {
            fatherName.setError("Field can not be empty");
            return false;
        } else {
            fatherName.setError(null);
            fatherName.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateMotherName() {
        String fullName_value = motherName.getEditText().getText().toString().trim();

        if (fullName_value.isEmpty()) {
            motherName.setError("Field can not be empty");
            return false;
        } else {
            motherName.setError(null);
            motherName.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateAge() {
        String fullName_value = age.getEditText().getText().toString().trim();

        if (fullName_value.isEmpty()) {
            age.setError("Field can not be empty");
            return false;
        } else {
            age.setError(null);
            age.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateAddress() {
        String fullName_value = address.getEditText().getText().toString().trim();

        if (fullName_value.isEmpty()) {
            address.setError("Field can not be empty");
            return false;
        } else {
            address.setError(null);
            address.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateDateB() {
        String fullName_value = dateOfBirth.getEditText().getText().toString().trim();

        if (fullName_value.isEmpty()) {
            dateOfBirth.setError("Field can not be empty");
            return false;
        } else {
            dateOfBirth.setError(null);
            dateOfBirth.setErrorEnabled(false);
            return true;
        }
    }
    private boolean validateMobile() {
        String fullName_value = mobileNo.getEditText().getText().toString().trim();

        if (fullName_value.isEmpty()) {
            mobileNo.setError("Field can not be empty");
            return false;
        } else {
            mobileNo.setError(null);
            mobileNo.setErrorEnabled(false);
            return true;
        }
    }

}
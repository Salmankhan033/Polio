package com.taif.polio.Common.SigninSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.taif.polio.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

public class SignUp2ndClass extends AppCompatActivity {

    //Variables
    ImageView backBtn2;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;
    Button next;
    String fullName, email, address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_2nd_class);

        //Hocks
        backBtn2 = findViewById(R.id.signup2_back_button);
        next = findViewById(R.id.signup_OTP_button);
        phoneNumber = findViewById(R.id.signup_mobile_number);
        countryCodePicker = findViewById(R.id.country_code_picker);

        //This button is for back action to 1st signUp screen
        backBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SignUp.class);
                //Add Transition
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(backBtn2, "transition_next_btn");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }

            }
        });


        //This button is for getting OTP code and moving to next OTP activity
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Get all values from previous screen "SignUp" using intent
                fullName = getIntent().getStringExtra("fullName");
                email = getIntent().getStringExtra("email");
                address = getIntent().getStringExtra("address");





                String _getUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim(); // Get Phone Number
                String phoneNo = "+" + countryCodePicker.getFullNumber() + _getUserEnteredPhoneNumber;  // Get both country code and Phone number
                Intent intent = new Intent(getApplicationContext(), LoginOTP.class);

                //Pass all fields data to next activity
                intent.putExtra("fullName", fullName);
                intent.putExtra("email", email);
                intent.putExtra("address", address);
                intent.putExtra("phoneNo", phoneNo);


                //Add Transition
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(next, "transition_next_btn");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {

                   startActivity(intent);
                }
            }
        });
    }


}

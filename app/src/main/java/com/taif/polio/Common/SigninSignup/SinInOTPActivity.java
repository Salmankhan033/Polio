package com.taif.polio.Common.SigninSignup;

import androidx.annotation.NonNull;
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

import com.taif.polio.Common.WelcomeScreen;
import com.taif.polio.NavigationDrawer.NavigationDrawerActivity;
import com.taif.polio.R;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SinInOTPActivity extends AppCompatActivity {

    //Variables
    ImageView cancel_button;
    PinView pinFromUser;
    String codeBySystem;
    Button verifyBtn;
    String  phoneNo;
    String job;
    //For Firebase Authentication
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otpcod);

        //Hocks
        cancel_button =findViewById(R.id.cross_button);
        pinFromUser = findViewById(R.id.pin_view);
        verifyBtn = findViewById(R.id.verify_OTP_button);
        mAuth = FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Waiting...");
        progressDialog.setMessage("Waiting PLease...");
        progressDialog.show();

        //This button is use to manually verify the OTP when user enter the code
        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
                //Add Transition
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(cancel_button, "transition_next_btn");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SinInOTPActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                } else {
                    startActivity(intent);
                }

            }
        });


        //This button is use to manually verify the OTP when user enter the code
        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String code = pinFromUser.getText().toString();
                if (!code.isEmpty()) {
                    verifyCode(code);
                }
            }
        });

        //here we get intent of previous activity signup2

        phoneNo = getIntent().getStringExtra("phoneNo");
        job=getIntent().getStringExtra("Job");

        //here we initialize function on name "sendVerificationCodeToUser" that send verification code to user
        sendVerificationCodeToUser(phoneNo);
    }

    //here we declare function on name "sendVerificationCodeToUser" that send verification code to user
    private void sendVerificationCodeToUser(String phoneNo) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNo,                  // Phone number to verify
                60,                   // Timeout duration
                TimeUnit.SECONDS,      // Unit of timeout
                this,         // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            // This is manual code verification, user wil enter it manually, then it will verify
            SinInOTPActivity.this.codeBySystem = s;
            progressDialog.dismiss();

        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            //This function will execute automatically on same the mobile, to verify the code
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                pinFromUser.setText(code);
                //here we initialize function on name "verifyCode" that match both user enter code and OTP.
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            //In case of OTP failed error message will show
            progressDialog.dismiss();
            Toast.makeText(SinInOTPActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    //here we declare function on name "verifyCode" that match both user enter code and OTP.
    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem, code);
        signInWithPhoneAuthCredential(credential);
        progressDialog.dismiss();
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Select A Activity
                            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
                                progressDialog.dismiss();
                                Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                                startActivity(intent);
                                 finish();




                            // Toast.makeText(LoginOTP.this, "verification Successful", Toast.LENGTH_LONG).show();











                        } else {

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                progressDialog.dismiss();
                                Toast.makeText(SinInOTPActivity.this, "verification Failed", Toast.LENGTH_LONG).show();

                            }
                        }
                    }
                });
    }

}

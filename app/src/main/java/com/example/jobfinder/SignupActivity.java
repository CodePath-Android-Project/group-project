package com.example.jobfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends AppCompatActivity {

    public static final String TAG = "Signup";
    private EditText etCreateUser;
    private EditText etCreatePassword;
    private EditText etEmailAddress;
    private EditText etPhoneNumber;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etCreateUser = findViewById(R.id.etCreateUser);
        etCreatePassword = findViewById(R.id.etCreatePassword);
        etEmailAddress = findViewById(R.id.etEmailAddress);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnSignUp = findViewById(R.id.btnCreateAccount);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the ParseUser
                ParseUser user = new ParseUser();
                String userName = etCreateUser.getText().toString();
                String userPassword = etCreatePassword.getText().toString();
                String userEmail = etEmailAddress.getText().toString();
                String userPhone = etPhoneNumber.getText().toString();

                // Set core properties
                user.setUsername(userName);
                user.setPassword(userPassword);
                user.setEmail(userEmail);
                // Set custom properties
                user.put("phone", "650-253-0000");
                // Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        if (e == null) {
                            // Hooray! Let them use the app now.
                            Toast.makeText(SignupActivity.this, "Sign up success!", Toast.LENGTH_SHORT).show();

                            //go to login activity on sign up success
                            goToLoginActivity();
                        } else {
                            // Sign up didn't succeed. Look at the ParseException
                            // to figure out what went wrong
                            Log.e(TAG, "Issue with sign up", e);
                            Toast.makeText(SignupActivity.this, "Issue with sign up", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void goToLoginActivity() {
        Intent intentLogin = new Intent(this, LoginActivity.class);
        startActivity(intentLogin);
        finish();
    }
}
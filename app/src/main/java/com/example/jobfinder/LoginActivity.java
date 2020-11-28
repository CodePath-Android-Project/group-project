package com.example.jobfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // if user already logged in, go to MainActivity on app launch
        if (ParseUser.getCurrentUser() != null) {
            goToMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Click on Login Button");
                String userName = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                loginUser(userName, password);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSignUpActivity();
            }
        });
    }

    private void loginUser(String userName, String password) {
        Log.i(TAG, "Attempt to login user");

        ParseUser.logInInBackground(userName, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) { // if there is a problem (the request did not succeed)
                    //TODO: better error handling to notify the user on why they cannot login to their account
                    Log.e(TAG, "Issue with login", e);
                    Toast.makeText(LoginActivity.this, "Issue with login", Toast.LENGTH_SHORT).show(); //improve this by specifying where the error is coming from
                    return;
                }
                //navigate to the main  activity if the user has signed in properly (the request has succeeded)
                goToMainActivity();
                //indicate to the user that they successfully logged in
                Toast.makeText(LoginActivity.this, "Login Success!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void goToMainActivity() {
        Intent intentMain = new Intent(this, MainActivity.class);
        startActivity(intentMain);
        //add animation to layout transition
//        overridePendingTransition(R.anim.left_in, R.anim.right_out);
        finish(); //finish the login activity once user login successfully
    }

    private void goToSignUpActivity() {
        Intent intentSignUp = new Intent(this, SignupActivity.class);
        startActivity(intentSignUp);
        finish();
    }
}
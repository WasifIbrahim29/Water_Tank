package com.example.aquatracker;
/*
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.aquatracker.R;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Email_edtxt;                  //variable declaration
    private EditText Pass_edtxt;
    /*private EditText confirm_edtxt;
    private EditText dev_no;
    private EditText address;

    private Button btn_confirm;

    //private Button Cancel_btn;

    private FirebaseAuth mAuth;         //firebase declare auth
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

       // FirebaseApp.initializeApp(this);

        Email_edtxt = (EditText) findViewById(R.id.Username_Edittext);      //linking of variables to buttons/edittexts
        Pass_edtxt = (EditText) findViewById(R.id.Password_editText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        /* confirm_edtxt = (EditText) findViewById(R.id.ConfirmPass_edittext);
        dev_no = (EditText) findViewById(R.id.DeviceNo_edittext);
        address = (EditText) findViewById(R.id.address_edittext);


        mAuth = FirebaseAuth.getInstance(); //new addition


        findViewById(R.id.confirm_button).setOnClickListener(this);       //Sherry code style
        findViewById(R.id.Cancel_button).setOnClickListener(this);

       // btn_confirm = (Button) findViewById(R.id.confirm_button);
       // Cancel_btn = (Button) findViewById(R.id.Cancel_button);

       /*btn_confirm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Toast.makeText(getApplicationContext(),"Youre in the signup Function",Toast.LENGTH_SHORT).show();
               signup();

           }
       });*/



       /*Cancel_btn.setOnClickListener(                        //setting function of cancel button
               new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       finish();
                   }
               });

    }
    private void registeruser()
    {

        String email = Email_edtxt.getText().toString().trim();
        String password = Pass_edtxt.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short, must be atleast 6 characters!", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        //create user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Signup Successful" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.

                        }
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignUpActivity.this, "Authentication failed" + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }
                                /*else {
                                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                    finish();
                                }
                    }
                });
    }



    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }


    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.confirm_button:
                registeruser();
            break;

            case R.id.Cancel_button:
                finish();
                startActivity(new Intent(this,loginactivity.class));
                break;


        }
    }
}*/


       //////////////////////////////////////////////////////////////////////SHERRY CODE//////////////////////


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    ProgressBar progressBar;
    EditText editTextEmail, editTextPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextEmail = (EditText) findViewById(R.id.Email_editText);
        editTextPassword = (EditText) findViewById(R.id.Newpassword_editText);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.confirm_button).setOnClickListener(this);
        findViewById(R.id.Cancel_button).setOnClickListener(this);
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Please enter a valid email");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Minimum length of password should be 6");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    // finish();
                    //startActivity(new Intent(SighnUpActivity.this, ProfileActivity.class));
                } else {

                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                        Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }


    @Override

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm_button:
                registerUser();
                break;

            case R.id.Cancel_button:
                finish();
                startActivity(new Intent(this, loginactivity.class));
                break;
        }
    }
}

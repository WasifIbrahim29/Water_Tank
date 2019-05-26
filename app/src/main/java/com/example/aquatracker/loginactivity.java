package com.example.aquatracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aquatracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginactivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    private static EditText username;
    private static EditText password;           //variable declaration
    private static Button Login_btn;
    private static Button signup_btn;

    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();


        username=(EditText)findViewById(R.id.Username_Edittext);
        password=(EditText)findViewById(R.id.Password_editText);
        Login_btn=(Button)findViewById(R.id.Login_button);
        signup_btn=(Button)findViewById(R.id.SignUp_Button);

        progressBar = (ProgressBar) findViewById(R.id.progressbar9);

        Login_btn.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        signin();
                        //Intent i1=new Intent(getBaseContext(),MainActivity.class);
                        //startActivity(i1);

                    }
                }
        );

        signup_btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View p)
            {
                Intent signup = new Intent("android.intent.action.MAIN3");
                startActivity(signup);                                             //takes user to signup page
            }


        });
    }

    private void signin() {

        String email = username.getText().toString().trim();
        String password1 = password.getText().toString().trim();

        if (email.isEmpty()) {
            username.setError("Email is required");
            username.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            username.setError("Please enter a valid email");
            username.requestFocus();
            return;
        }

        if (password1.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }


        mAuth.signInWithEmailAndPassword(email, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    //finish();
                    Intent intent11 = new Intent(loginactivity.this, MainActivity.class);
                    intent11.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent11);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
};

/*
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }
mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            if (task.isSuccessful()) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.getException());
                Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
                updateUI(null);
            }

            // ...
        }
    });*/


package com.example.aquatracker;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.aquatracker.Utils.FirebaseMethods;
import com.example.aquatracker.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static Button stats_btn;
    private static Button setlvl_btn;
    private static Button logout_btn;

    private static final String TAG = "MainActivity";


    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;


    TextView waterLevel;
    Button overhead;
    Button underground;

    private FirebaseAuth.AuthStateListener mAuthListener;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();

        String id=mAuth.getCurrentUser().getUid();

        setupFirebaseAuth();

        logout_btn=(Button)findViewById(R.id.logout_button);
        overhead=findViewById(R.id.OH_btn);
        underground=findViewById(R.id.UG_btn);

        overhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent41 = new Intent(getBaseContext(),SetLevelOverhead.class);
                startActivity(intent41);
            }
        });

        underground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent41 = new Intent(getBaseContext(),SetLevelUnderground.class);
                startActivity(intent41);
            }
        });
        /*

        stats_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent41 = new Intent(getBaseContext(),StatsActivity.class);
                        startActivity(intent41);
                    }
                }
        );

        setlvl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent42=new Intent(getBaseContext(),SetLevelOverhead.class);
                startActivity(intent42);
            }
        });

        */

        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();


        final View myView= (DrawingTheBall) findViewById(R.id.id1);
        waterLevel=findViewById(R.id.waterLevel);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                getUserLevel(new FirebaseCallBack() {
                    @Override
                    public void onCallback(String mainLevel ) {


                        ((DrawingTheBall) myView).setProgress(Integer.valueOf(mainLevel));
                        waterLevel.setText("Water Level: "+ mainLevel + " %");
                        myView.invalidate();


                    }
                },dataSnapshot);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

 public void logout()
 {/////////////////////////
     mAuth.signOut();
     Intent intent42=new Intent(getApplicationContext(),loginactivity.class);
     startActivity(intent42);



 }

    private void getUserLevel(final FirebaseCallBack firebaseCallback, DataSnapshot dataSnapshot){

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();

        //String current_id= Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

        //Log.d(TAG, "getUsers: "+ current_id);

        final FirebaseMethods firebaseMethods= new FirebaseMethods(this);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String mainLevel=firebaseMethods.getMainLevel(dataSnapshot,mAuth.getCurrentUser().getUid());
                firebaseCallback.onCallback(mainLevel);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public interface FirebaseCallBack {
        void onCallback(String mainLevel);
    }

    private void setupFirebaseAuth(){
        Log.d(TAG, "setupFirebaseAuth: setting up firebase auth.");

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                //check if the user is logged in
                checkCurrentUser(user);

                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }

    private void checkCurrentUser(FirebaseUser user){
        Log.d(TAG, "checkCurrentUser: checking if user is logged in.");

        if(user == null){
            Intent intent = new Intent(getApplicationContext(), loginactivity.class);
            startActivity(intent);
        }
    }

    private void avoidLogin(){
        Log.d(TAG, "checkCurrentUser: checking if user is logged in.");

        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user= firebaseAuth.getCurrentUser();

        if(user != null){
            Intent intent = new Intent(getApplicationContext(), loginactivity.class);
            startActivity(intent);
        }
    }


}

package com.example.aquatracker.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import com.example.aquatracker.model.User;
import com.google.android.gms.common.images.ImageManager;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class FirebaseMethods extends Activity {

    private static final String TAG = "FirebaseMethods";
    private Context mContext;


    //firebase
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private DatabaseReference myRef1;
    private String userID;
    private DatabaseReference mUserDatabase;
    private StorageReference mStorageReference;
    ArrayList<String> specialitiesMatched= new ArrayList<>();
    final int PERMISSION_REQUEST_CODE=1;

    public FirebaseMethods(Context context) {
        //Log.d(TAG, "FirebaseMethods: hello sup?");
        //mAuth = FirebaseAuth.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        myRef1 = mFirebaseDatabase.getReference();
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mContext = context;
        if(mAuth.getCurrentUser() != null){
            userID = mAuth.getCurrentUser().getUid();
        }
    }

    public void addNewUser(String userCurrentID,String address, String deviceno,String email, String password,String undergroundLevel,String overheadLevel,String mainLevel){

        User user = new User( address, deviceno ,email,password,undergroundLevel,overheadLevel ,mainLevel);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("users");

        myRef.child(userCurrentID).setValue(user);

        //deleteUselessQuestions();

    }

    public String getMainLevel(DataSnapshot dataSnapshot, String userID) {

        String mainLevel = "";

        for (DataSnapshot dataSnapshot1 : dataSnapshot.child("users").getChildren()) {

            if (dataSnapshot1.getKey().equals(userID)) {
                mainLevel = dataSnapshot1.child("mainLevel").getValue().toString();
            }
        }

        return mainLevel;
    }

    public void setOverheadLevel(String overheadLevel,String userID){
        myRef = mFirebaseDatabase.getReference();



        myRef.child("users")
                .child(userID)
                .child("overheadLevel")
                .setValue(overheadLevel);


        myRef.child("users")
                .child(userID)
                .child("mainLevel")
                .setValue(overheadLevel);
    }

    public void setUndergroundLevel(String undergroundLevel,String userID){
        myRef = mFirebaseDatabase.getReference();



        myRef.child("users")
                .child(userID)
                .child("undergroundLevel")
                .setValue(undergroundLevel);

    }
}

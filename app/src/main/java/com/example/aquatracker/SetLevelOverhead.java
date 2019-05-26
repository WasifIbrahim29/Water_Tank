package com.example.aquatracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.aquatracker.Utils.FirebaseMethods;
import com.google.firebase.auth.FirebaseAuth;

public class SetLevelOverhead extends AppCompatActivity {

    private static Button setLevel;
    private static ImageButton back_btn;


    SeekBar seekBar;
    TextView waterLevel;
    String tankLevel;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_level_overhead);
        mAuth=FirebaseAuth.getInstance();

        getSupportActionBar().setTitle("Overhead Tank");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setLevel=(Button)findViewById(R.id.setLevel);


        setLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMethods firebaseMethods= new FirebaseMethods(getApplicationContext());
                firebaseMethods.setOverheadLevel(tankLevel,mAuth.getCurrentUser().getUid());
                finish();

            }
        });

        seekBar=findViewById(R.id.seekbar);

        final View myView= (DrawingTheBall) findViewById(R.id.id1);
        waterLevel=findViewById(R.id.waterLevel);


        seekBar.setProgress(20);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                ((DrawingTheBall) myView).setProgress(progress);
                waterLevel.setText("Water Level: "+ progress + " %");
                tankLevel=progress+"";
                myView.invalidate();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}

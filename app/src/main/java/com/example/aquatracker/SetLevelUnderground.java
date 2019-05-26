package com.example.aquatracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.aquatracker.Utils.FirebaseMethods;
import com.google.firebase.auth.FirebaseAuth;

public class SetLevelUnderground extends AppCompatActivity {

    SeekBar seekBar;
    private static Button setLevel;
    TextView waterLevel;
    String tankLevel;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_level_underground);
        mAuth=FirebaseAuth.getInstance();

        getSupportActionBar().setTitle("Underground Tank");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        seekBar=findViewById(R.id.seekbar);

        final View myView= (DrawingTheBall) findViewById(R.id.id1);
        waterLevel=findViewById(R.id.waterLevel);

        setLevel=(Button)findViewById(R.id.setLevel);


        setLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseMethods firebaseMethods= new FirebaseMethods(getApplicationContext());
                firebaseMethods.setUndergroundLevel(tankLevel,mAuth.getCurrentUser().getUid());
                finish();

            }
        });


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

package com.example.aquatracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class SetLevelOverhead extends AppCompatActivity {

    private static Button UnderG_btn;
    private static ImageButton back_btn;


    SeekBar seekBar;
    TextView waterLevel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_level_overhead);

        getSupportActionBar().setTitle("Overhead Tank");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        UnderG_btn=(Button)findViewById(R.id.UG_btn);


        UnderG_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent51=new Intent(getBaseContext(),SetLevelUnderground.class);
                startActivity(intent51);
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

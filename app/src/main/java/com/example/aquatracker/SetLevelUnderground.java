package com.example.aquatracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class SetLevelUnderground extends AppCompatActivity {

    SeekBar seekBar;
    TextView waterLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_level_underground);

        getSupportActionBar().setTitle("Underground Tank");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

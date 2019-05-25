package com.example.aquatracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static Button stats_btn;
    private static Button setlvl_btn;
    private static Button logout_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stats_btn= (Button)findViewById(R.id.Statistics_button);
        setlvl_btn=(Button)findViewById(R.id.SetLevel_button);
        logout_btn=(Button)findViewById(R.id.logout_button);

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
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });


    }

 public void logout()
 {/////////////////////////
     Intent intent43=new Intent(getBaseContext(),loginactivity.class);       //complete firebase logout user to be implemented
     startActivity(intent43);


 }


}

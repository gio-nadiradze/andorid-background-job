package com.site.locate.location_sender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.button);
        final Button button_stop = (Button) findViewById(R.id.button_stop);
        final RelativeLayout MainActivity = (RelativeLayout) findViewById(R.id.main_box);
        final TextView textView = (TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                button.setEnabled(false);
                MainActivity.setBackgroundColor(getResources().getColor(R.color.color2));

                Intent i= new Intent(MainActivity .this, MRService.class);
                startService(i);
            }
        });

        button_stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                button_stop.setEnabled(false);
                MainActivity.setBackgroundColor(getResources().getColor(R.color.color3));

                Intent i= new Intent(MainActivity .this, MRService.class);
                stopService(i);

                finish();
                System.exit(0);
            }
        });


        LocalBroadcastManager.getInstance(this).registerReceiver(
                new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        double latitude = intent.getDoubleExtra(MRService.EXTRA_LATITUDE, 0);
                        double longitude = intent.getDoubleExtra(MRService.EXTRA_LONGITUDE, 0);
                        textView.setText("Lat: " + latitude + ", Lng: " + longitude);
                    }
                }, new IntentFilter(MRService.ACTION_LOCATION_BROADCAST)
        );
    }
}

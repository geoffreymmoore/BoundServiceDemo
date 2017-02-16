package com.moore.boundservicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.moore.boundservicedemo.MyService.MyLocalBinder;

public class TimeActivity extends AppCompatActivity {

    MyService myService; // reference to class
    boolean isBound = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time);

        ComponentName i = startService(new Intent(this, MyService.class));
        bindService(new Intent(this,MyService.class), myConnection, BIND_AUTO_CREATE); //bind the service

    }

    // display the date and time
    public void showTime(View view) {
        String currentTime = myService.getCurrentTime();
        TextView timeTV = (TextView) findViewById(R.id.timeTV);
        timeTV.setText(currentTime);
    }

    // action class that will bind to service
    private ServiceConnection myConnection = new ServiceConnection() {

        // what is it that you want to do when you connect
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLocalBinder binder = (MyLocalBinder) service;
            myService = binder.getService(); // get service to the class
            isBound = true; // bound to a service
        }

        // what do you want to happen when you disconnect from service
        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false; // disconnect the service
        }
    };
}

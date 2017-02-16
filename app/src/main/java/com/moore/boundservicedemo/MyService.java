package com.moore.boundservicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyService extends Service {

    // create an object that is going to be the binder object
    private final IBinder myBinder = new MyLocalBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return myBinder;
    }

    public String getCurrentTime(){

        SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss", Locale.US);

        return (df.format(new Date()));
    }

    // a class that has the ability to bind
    public class MyLocalBinder extends Binder {

        MyService getService(){
            return MyService.this;
        }
    }
}

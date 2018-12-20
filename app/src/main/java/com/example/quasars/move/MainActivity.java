package com.example.quasars.move;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.content.res.Resources;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.os.Handler;
import android.os.Message;

import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    SensorManager sm;
    Sensor sr;
//    ImageView t1;
    ConstraintLayout layout;
    double mx = 0, my = 0;


    LinearLayout lo;
    int[] images = {R.drawable.t1,R.drawable.t2};
    int SIGN = 17;
    int num = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();



        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sr = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        t1 = (ImageView) findViewById(R.id.imageView1);
        layout = (ConstraintLayout) findViewById(R.id.layout);
    }

    private void initView(){
        lo= (LinearLayout) findViewById(R.id.linearlayout_main);
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                if(msg.what == SIGN){
                    Resources resources = getResources();
                    Drawable btnDrawable = resources.getDrawable(images[num++]);
                    lo.setBackgroundDrawable(btnDrawable);
                    if(num >= images.length){
                        num = 0;
                    }
                }
            }
        };
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = SIGN;
                handler.sendMessage(msg);
            }
        }, 1000, 100);

    }

    @Override
    public void onSensorChanged(SensorEvent event){
        if(mx==0){
            mx = (layout.getWidth()-lo.getWidth())/20.0;
            my = (layout.getHeight()-lo.getHeight())/20.0;
        }
        ConstraintLayout.LayoutParams parms =
                (ConstraintLayout.LayoutParams) lo.getLayoutParams();
        parms.leftMargin = (int)((10-event.values[0]) * mx);
        //parms.topMargin = (int)((10+event.values[1]) * my);
        lo.setLayoutParams(parms);

    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){
    }
    @Override
    protected void onResume(){
        super.onResume();
        sm.registerListener(this,sr,SensorManager.SENSOR_DELAY_GAME);
    }

}

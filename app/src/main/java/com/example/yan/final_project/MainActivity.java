package com.example.yan.final_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    Button play;
    TextView showpoint;
    int point;
    MediaPlayer mp=new MediaPlayer();

    int xchange;
    int before,after;



    SensorManager sm;
    Sensor sr;
    //    ImageView t1;
    ConstraintLayout layout;
    double mx = 0, my = 0;
    LinearLayout lo;
    int[] images = {R.drawable.t1,R.drawable.t2,R.drawable.t3,R.drawable.t4};
    int SIGN = 17;
    int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play=(Button)findViewById(R.id.play);
        showpoint=(TextView)findViewById(R.id.showpoint) ;

        mp=MediaPlayer.create(this,R.raw.smoke);
        mp.start();


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_main);
        initView();
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sr = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        t1 = (ImageView) findViewById(R.id.imageView1);
        layout = (ConstraintLayout) findViewById(R.id.layout);
    }
    private void initView(){
        lo= (LinearLayout) findViewById(R.id.lo);
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg)
            {
                super.handleMessage(msg);
                if(msg.what == SIGN){
                    Resources resources = getResources();
                    if(xchange>0){
                        Drawable btnDrawable = resources.getDrawable(images[num++]);
                        lo.setBackgroundDrawable(btnDrawable);
                        if(num >= 2){
                            num = 0;
                        }
                    }
                    else
                    {
                        Drawable btnDrawable = resources.getDrawable(images[num++]);
                        lo.setBackgroundDrawable(btnDrawable);
                        if(num >= 4){
                            num = 2;
                        }
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
    public void play(View v)
    {

        Intent it = new Intent(this ,play_activity.class);
        startActivityForResult(it,123);
    }


    @Override
    public void onSensorChanged(SensorEvent event)
    {
        ConstraintLayout.LayoutParams parms = (ConstraintLayout.LayoutParams) lo.getLayoutParams();
        before=parms.leftMargin;

        if(mx==0){
            mx = (layout.getWidth()-lo.getWidth())/20.0;
            my = (layout.getHeight()-lo.getHeight())/20.0;
        }




        parms.leftMargin = (int)((10-event.values[0]) * mx);
        //parms.topMargin = (int)((10+event.values[1]) * my);
        lo.setLayoutParams(parms);

        after= parms.leftMargin;
        showpoint.setText("before"+before+"   after"+after);

        xchange=after-before;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        sm.registerListener(this,sr,SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

    }


    public void rank(View v)
    {

        Intent it = new Intent(this ,rank.class);
        startActivityForResult(it,456);
    }


    private int number6;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.userdata:
                Toast.makeText(this, "users data selected", Toast.LENGTH_SHORT).show();
                changeSettings();
                return true;

            case R.id.exit:
                finish();
                return true;


            default:return super.onOptionsItemSelected(item);
        }

    }
    private void changeSettings()
    {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        //new新的視窗
        final String[] settingList = {"105360006陳彥丞 ", "105360028鍾宇弼" , "105360033顏偉哲"};
        //在新視窗中建立字串陣列
        alertDialog.setTitle("使用者資料");
        alertDialog.setItems(settingList, new DialogInterface.OnClickListener()
        {
            //將字串陣列匯入(監聽到被按下)
            @Override
            public void onClick(DialogInterface dialog, final int which)
            {
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);

                switch(which){
                    case 0:number6 = 1;

                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                };
                // alertDialog.show();
            }
        });
        alertDialog.show();
    }

}

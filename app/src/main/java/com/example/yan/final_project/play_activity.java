package com.example.yan.final_project;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Trace;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class play_activity extends AppCompatActivity
    implements View.OnClickListener
{
    int target[][]=new int[5][3];
    ImageView target0,target1,target2;
    Button shot0,shot1,shot2;
    int delaySecond=0;
    int origin_top,origin_botton;
    int countDown=3;;
    int start=0;
    Toast toast;
    MediaPlayer gameBGM=new MediaPlayer();


    int a=0,b=0,c=0;
    private int point;
    private Long startTime;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_activity);


        shot0=(Button)findViewById(R.id.shot0);
        shot1=(Button)findViewById(R.id.shot1);
        shot2=(Button)findViewById(R.id.shot2);

        shot0.setOnClickListener(this);
        shot1.setOnClickListener(this);
        shot2.setOnClickListener(this);

        target0 =(ImageView)findViewById(R.id.target0);
        target1 =(ImageView)findViewById(R.id.target1);
        target2 =(ImageView)findViewById(R.id.target2);

        handler.post(count123);
        begin_target();

        gameBGM=MediaPlayer.create(this,R.raw.game);
        gameBGM.setLooping(true);
        gameBGM.start();
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        gameBGM.stop();
        gameBGM.release();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }
    @Override
    public void onClick(View v)
    {
        determine_shot_target(v);
    }
    private Runnable updateTimer = new Runnable()
    {
        public void run()
        {
            final TextView time = (TextView) findViewById(R.id.timer);
            Long spentTime = System.currentTimeMillis() - startTime;

            Long seconds = 30-(spentTime/1000) % 60;                //計算目前已過秒數
            time.setText(""+seconds);
            handler.postDelayed(this, 1000);

            if(seconds<=0)
            {
                time.setText("0");
                if (a==0)
                {
                    delaySecond=1;
                }
                endGame();
                a++;
            }
        }
    };
    private Runnable delay = new Runnable()
    {
        public void run()
        {
            target0.layout(target0.getLeft(), origin_top, target0.getRight(), origin_botton);
            target1.layout(target1.getLeft(), origin_top, target1.getRight(), origin_botton);
            target2.layout(target2.getLeft(), origin_top, target2.getRight(), origin_botton);

            delaySecond=0;
        }

    };
    private Runnable count123 = new Runnable()
    {
        public void run()
        {
            countDown();
            countDown--;
            handler.postDelayed(this, 1000);

            if (countDown<=-1)
            {
                handler.removeCallbacks(this);
            }
        }

    };
    public void countDown()
    {

        if (countDown==3)
        {
            toast = Toast.makeText(this, "準備", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }
        else if(countDown<=0)
        {
            toast = Toast.makeText(this, "開始", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            start=1;
            settingTimer();
        }

    }

    public void settingTimer()
    {
        startTime = System.currentTimeMillis();
        //設定定時要執行的方法
        handler.removeCallbacks(updateTimer);
        //        //設定Delay的時間
        handler.postDelayed(updateTimer, 1000);
    }
    public void endGame ()
    {
        handler.postDelayed(delay, 3000);
        if (b==0)
        {
            toast = Toast.makeText(this, "遊戲結束", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();

            b++;
        }

        if (delaySecond==0&&c==0)
        {
            Intent it = new Intent(this ,EndOfGame.class);
            Bundle bundle =new Bundle();
            bundle.putInt("point",point);
            it.putExtras(bundle);
            startActivityForResult(it,123);
            finish();
            c++;
        }
    }
    public void begin_target ()
    {
        point=0;
        for (int i=0;i<3;i++)
        {
            for (int j= 0;j<5;j++)
            {
                target[j][i]=0;
            }
        }

        for (int i=0;i<5;i++)
        {
            int random = (int)(Math.random()* 3);
            target[i][random]=1;
        }
        setting_print_target();
    }
    public void determine_shot_target (View v)
        {
            if (delaySecond==0 && start==1)
            {
                origin_top=target1.getTop();
                origin_botton=target1.getBottom();


                if (v.getId()==R.id.shot0 && target[0][0]==1)
                {
                    findViewById(R.id.target0).setVisibility(View.INVISIBLE);
                    target[0][0]=0;
                    make_new_target();
                    setting_print_target();
                    point=point+1;
                }
                else if (v.getId()==R.id.shot1 && target[0][1]==1)
                {
                    findViewById(R.id.target1).setVisibility(View.INVISIBLE);
                    target[0][1]=0;
                    make_new_target();
                    setting_print_target();
                    point=point+1;
                }
                else if (v.getId()==R.id.shot2 && target[0][2]==1)
                {
                    findViewById(R.id.target2).setVisibility(View.INVISIBLE);
                    target[0][2]=0;
                    make_new_target();
                    setting_print_target();
                    point=point+1;
                }
                else if (v.getId()==R.id.shot0)
                {
                    if(target[0][1]==1)
                    {
                        target1.layout(target1.getLeft(), target1.getTop()-80, target1.getRight(), target1.getBottom()-80);
                    }
                    else if(target[0][2]==1)
                    {
                        target2.layout(target2.getLeft(), target2.getTop()-80, target2.getRight(), target2.getBottom()-80);
                    }
                    delaySecond=1;
                    handler.postDelayed(delay, 500);
                }
                else if (v.getId()==R.id.shot1)
                {
                    if(target[0][0]==1)
                    {
                        target0.layout(target0.getLeft(), target0.getTop()-80, target0.getRight(), target0.getBottom()-50);
                    }
                    else if(target[0][2]==1)
                    {
                        target2.layout(target2.getLeft(), target2.getTop()-80, target2.getRight(), target2.getBottom()-80);
                    }
                    delaySecond=1;
                    handler.postDelayed(delay, 500);
                }
                else if (v.getId()==R.id.shot2)
                {
                    if(target[0][0]==1)
                    {
                        target0.layout(target0.getLeft(), target0.getTop()-80, target0.getRight(), target0.getBottom()-80);
                    }
                    else if(target[0][1]==1)
                    {
                        target1.layout(target1.getLeft(), target1.getTop()-80, target1.getRight(), target1.getBottom()-80);
                    }
                    delaySecond=1;
                    handler.postDelayed(delay, 500);
                }
            }
    }
    public void make_new_target()
    {
        if (target[0][0]==0 && target[0][1]==0 && target[0][2]==0)
        {
            down_array_target();
            int random = (int)(Math.random()* 3);
            target[4][random]=1;
        }
    }
    public void setting_print_target()
    {
        if (target[0][0]==1)
        {
            findViewById(R.id.target0).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.target0).setVisibility(View.INVISIBLE);
        }
        if (target[0][1]==1)
        {
            findViewById(R.id.target1).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.target1).setVisibility(View.INVISIBLE);
        }
        if (target[0][2]==1)
        {
            findViewById(R.id.target2).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.target2).setVisibility(View.INVISIBLE);
        }

        if (target[1][0]==1)
        {
            findViewById(R.id.target10).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.target10).setVisibility(View.INVISIBLE);
        }
        if (target[1][1]==1)
        {
            findViewById(R.id.target11).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.target11).setVisibility(View.INVISIBLE);
        }
        if (target[1][2]==1)
        {
            findViewById(R.id.target12).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.target12).setVisibility(View.INVISIBLE);
        }

        if (target[2][0]==1)
        {
            findViewById(R.id.target20).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.target20).setVisibility(View.INVISIBLE);
        }
        if (target[2][1]==1)
        {
            findViewById(R.id.target21).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.target21).setVisibility(View.INVISIBLE);
        }
        if (target[2][2]==1)
        {
            findViewById(R.id.target22).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.target22).setVisibility(View.INVISIBLE);
        }

        if (target[3][0]==1)
        {
            findViewById(R.id.target30).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.target30).setVisibility(View.INVISIBLE);
        }
        if (target[3][1]==1)
        {
            findViewById(R.id.target31).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.target31).setVisibility(View.INVISIBLE);
        }
        if (target[3][2]==1)
        {
            findViewById(R.id.target32).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.target32).setVisibility(View.INVISIBLE);
        }

        if (target[4][0]==1)
        {
            findViewById(R.id.target40).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.target40).setVisibility(View.INVISIBLE);
        }
        if (target[4][1]==1)
        {
            findViewById(R.id.target41).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.target41).setVisibility(View.INVISIBLE);
        }
        if (target[4][2]==1)
        {
            findViewById(R.id.target42).setVisibility(View.VISIBLE);
        }
        else
        {
            findViewById(R.id.target42).setVisibility(View.INVISIBLE);
        }
    }
    public void down_array_target()
    {
        for (int i=0;i<4;i++)
        {
            target[i][0]=target[i+1][0];
            target[i][1]=target[i+1][1];
            target[i][2]=target[i+1][2];
        }
        target[4][0]=0;
        target[4][1]=0;
        target[4][2]=0;
    }
}

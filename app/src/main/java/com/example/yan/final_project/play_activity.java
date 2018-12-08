package com.example.yan.final_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class play_activity extends AppCompatActivity
    implements View.OnClickListener
{
    int target[]=new int[3];
    ImageView hit0,hit1,hit2;
    Button shot0,shot1,shot2;
    TextView test;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_activity);

        test=(TextView)findViewById(R.id.textView);

        shot0=(Button)findViewById(R.id.shot0);
        shot1=(Button)findViewById(R.id.shot1);
        shot2=(Button)findViewById(R.id.shot2);

        shot0.setOnClickListener(this);
        shot1.setOnClickListener(this);
        shot2.setOnClickListener(this);


        begin_target();

    }


    @Override
    public void onClick(View v)
    {
        determine_hit(v);
        make_new_target();
    }





    public void begin_target ()
    {
        for (int i=0;i<3;i++)
        {
            target[i]=0;
        }
        int random = (int)(Math.random()* 3);
        target[random]=1;



        if (target[0]==1)
        {
            findViewById(R.id.hit0).setVisibility(View.VISIBLE);
        }
        if (target[1]==1)
        {
            findViewById(R.id.hit1).setVisibility(View.VISIBLE);
        }
        if (target[2]==1)
        {
            findViewById(R.id.hit2).setVisibility(View.VISIBLE);
        }
    }

    public void determine_hit (View v)
    {
        if (v.getId()==R.id.shot0)
        {
            findViewById(R.id.hit0).setVisibility(View.INVISIBLE);
            target[0]=0;
            test.setText(" 1");
        }

        if (v.getId()==R.id.shot1)
        {
            findViewById(R.id.hit1).setVisibility(View.INVISIBLE);
            target[1]=0;
            test.setText(" 2");
        }

        if (v.getId()==R.id.shot2)
        {
            findViewById(R.id.hit2).setVisibility(View.INVISIBLE);
            target[2]=0;
            test.setText(" 3");
        }
    }

    public void make_new_target()
    {
        if (target[0]==0 && target[1]==0 && target[2]==0)
        {
            int random = (int)(Math.random()* 3);
            target[random]=1;

            if (random==0)
            {
                findViewById(R.id.hit0).setVisibility(View.VISIBLE);
            }
            else if (random==1)
            {
                findViewById(R.id.hit1).setVisibility(View.VISIBLE);
            }
            else if (random==2)
            {
                findViewById(R.id.hit2).setVisibility(View.VISIBLE);
            }
        }

    }
}

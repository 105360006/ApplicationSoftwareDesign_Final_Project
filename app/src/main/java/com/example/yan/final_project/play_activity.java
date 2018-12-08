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
    int target[][]=new int[5][3];
    ImageView hit0,hit1,hit2;
    Button shot0,shot1,shot2;
    TextView test;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_activity);

//        test=(TextView)findViewById(R.id.textView);

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
        determine_shot_target(v);

    }





    public void begin_target ()
    {
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
        if (v.getId()==R.id.shot0 && target[0][0]==1)
        {
            findViewById(R.id.target0).setVisibility(View.INVISIBLE);
            target[0][0]=0;
            make_new_target();
            setting_print_target();
        }

        if (v.getId()==R.id.shot1 && target[0][1]==1)
        {
            findViewById(R.id.target1).setVisibility(View.INVISIBLE);
            target[0][1]=0;
            make_new_target();
            setting_print_target();
        }

        if (v.getId()==R.id.shot2 && target[0][2]==1)
        {
            findViewById(R.id.target2).setVisibility(View.INVISIBLE);
            target[0][2]=0;
            make_new_target();
            setting_print_target();
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

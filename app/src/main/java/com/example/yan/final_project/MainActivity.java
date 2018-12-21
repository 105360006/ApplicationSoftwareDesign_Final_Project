package com.example.yan.final_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    Button play;
    TextView showpoint;
    int point;
    MediaPlayer mp=new MediaPlayer();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play=(Button)findViewById(R.id.play);

//        mp=MediaPlayer.create(this,R.raw.smoke);
//        mp.start();

    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();

    }

    public void play(View v)
    {

        Intent it = new Intent(this ,play_activity.class);
        startActivityForResult(it,123);
    }


    public void rank(View v)
    {

        Intent it = new Intent(this ,rank.class);
        startActivityForResult(it,456);
    }



}

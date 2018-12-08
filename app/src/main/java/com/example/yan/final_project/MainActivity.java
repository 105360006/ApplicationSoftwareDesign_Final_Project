package com.example.yan.final_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    Button play;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play=(Button)findViewById(R.id.play);
    }
    public void play(View v)
    {
        Intent it = new Intent(this ,play_activity.class);
        startActivityForResult(it,123);
    }
}

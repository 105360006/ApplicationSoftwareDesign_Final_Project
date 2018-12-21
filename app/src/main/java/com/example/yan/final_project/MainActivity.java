package com.example.yan.final_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    Button play;
    TextView showpoint;
    MediaPlayer mp;
    int point;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play=(Button)findViewById(R.id.play);
    }

    public void play(View v)
    {
        mp=MediaPlayer.create(this,R.raw.music);
        mp.start();
        Intent it = new Intent(this ,play_activity.class);
        startActivityForResult(it,123);

    }
    private int number6;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
    private void changeSettings() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        //new新的視窗
        final String[] settingList = {"105360006陳彥丞 ", "105360028鍾宇弼" , "105360033顏偉哲"};
        //在新視窗中建立字串陣列
        alertDialog.setTitle("使用者資料");
        alertDialog.setItems(settingList, new DialogInterface.OnClickListener() {
            //將字串陣列匯入(監聽到被按下)
            @Override
            public void onClick(DialogInterface dialog, final int which) {
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

package com.example.yan.final_project;

        import android.content.Intent;
        import android.media.MediaPlayer;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.BufferedInputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.IOException;
        import java.io.BufferedWriter;
        import java.io.FileWriter;
        import java.io.FileOutputStream;

public class EndOfGame extends AppCompatActivity
{

    int point;
    TextView showpoint;
    TextView txtRead;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_game);

        showpoint=(TextView)findViewById(R.id.showpoint);
        txtRead = (TextView) findViewById(R.id.textView2);

        Intent it =getIntent();
        Bundle bundle=it.getExtras();
        point=bundle.getInt("point");

        point=point*point;
        showpoint.setText(""+point);

        pointRecord(point);
        reader();


    }

    public void goback(View v)
    {


        finish();

    }


    public void playagain(View v)
    {

        Intent it = new Intent(this ,play_activity.class);
        startActivityForResult(it,123);
    }

    public void pointRecord(int p)
    {
        FileOutputStream fos = null;
        try
        {
            String a= Integer.toString(p);
            fos = openFileOutput("pointRecord.txt", MODE_PRIVATE);
            fos.write(a.getBytes());
            fos.close();
            File file = new File(getFilesDir() + "/" + fos);
            Toast.makeText(getApplicationContext(), file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    private void reader()
    {
        FileInputStream fos = null;
        BufferedInputStream buffered = null;
        txtRead.setText("");
        try
        {
            fos = openFileInput("pointRecord.txt");
            buffered = new BufferedInputStream(fos);
            byte[] buffbyte = new byte[200];
            txtRead.setText("");
            do {
                int flag = buffered.read(buffbyte);
                if (flag == -1) {
                    break;
                } else {
                    txtRead.append(new String(buffbyte), 0, flag);
                }
            }while (true);
            buffered.close();

        }
        catch (Exception e)
        {
        e.printStackTrace();
        }
    }
}

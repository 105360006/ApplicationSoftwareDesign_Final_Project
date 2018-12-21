package com.example.yan.final_project;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.BufferedInputStream;
        import java.io.BufferedOutputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.IOException;
        import java.io.BufferedWriter;
        import java.io.FileWriter;
        import java.io.FileOutputStream;
        import java.lang.reflect.Array;
        import java.util.Arrays;
        import java.util.Comparator;

public class EndOfGame extends AppCompatActivity
{
    int point[]=new int[11];
    int pointTemp;
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
        pointTemp=bundle.getInt("point");

        showpoint.setText(""+pointTemp*pointTemp);


        zeroRecord();
        reader();
        sortRecord();

        pointRecord(point);
    }


    public void goback(View v)
    {
//        for(int i=10;i>=0;i--)
//        {
//            txtRead.append(" "+point[i]+"\n");
//        }

        finish();
    }
    public void sortRecord()
    {
        point[0]=pointTemp;

        Arrays.sort(point);
    }

      public void playagain(View v)
    {

        Intent it = new Intent(this ,play_activity.class);
        startActivityForResult(it,123);
        finish();
    }

    public void zeroRecord()
    {
        for(int i=0;i<=10;i++)
        {
            point[i]=0;
        }
    }

    public void pointRecord(int p[])
    {
        FileOutputStream fos = null;
        BufferedOutputStream buffered = null;
        try
        {
            fos = openFileOutput("pointRecord.txt", MODE_PRIVATE);
            buffered =new BufferedOutputStream(fos);

//            byte[] buffbyte = new byte[200];
            for(int i=0;i<=10;i++)
            {
                buffered.write(p[i]);
            }

            buffered.close();
            File file = new File(getFilesDir() + "/" + fos);

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

        try
        {
            fos = openFileInput("pointRecord.txt");
            buffered = new BufferedInputStream(fos);
//            byte[] buffbyte = new byte[200];



            for(int i=0;i<=10;i++)
            {
                point[i]=buffered.read();
            }

            buffered.close();

        }
        catch (Exception e)
        {
        e.printStackTrace();
        }
    }

}

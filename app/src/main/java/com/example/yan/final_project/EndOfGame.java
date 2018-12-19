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

public class EndOfGame extends AppCompatActivity
{
    int point[]=new int[11];
    int point2[]=new int[11];
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

        pointTemp=pointTemp*pointTemp;
        showpoint.setText(""+pointTemp);

        for(int i=0;i<=10;i++)
        {
            point[i]=i*25;
        }

        pointRecord(point);
        reader();

    }

    public void goback(View v)
    {

        for(int i=0;i<=10;i++)
        {
            txtRead.append(" "+point2[i]+"\n");
        }


//        txtRead.setText(point2[0]+"\n"+point2[1]+"\n"+point2[2]+"\n"+point2[3]+"\n"+point2[4]+"\n");
//        finish();
    }


    public void playagain(View v)
    {

//        Intent it = new Intent(this ,play_activity.class);
//        startActivityForResult(it,123);
    }

    public void pointRecord(int p[])
    {
        FileOutputStream fos = null;
        BufferedOutputStream buffered = null;
        try
        {
            fos = openFileOutput("pointRecord.txt", MODE_PRIVATE);
            buffered =new BufferedOutputStream(fos);

            byte[] buffbyte = new byte[200];
            for(int i=0;i<=10;i++)
            {


                buffered.write(p[i]);

            }

            buffered.close();
//            fos.close();

            File file = new File(getFilesDir() + "/" + fos);
//            Toast.makeText(getApplicationContext(), file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
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
//            do {
//                int flag = buffered.read(buffbyte);
//                if (flag == -1) {
//                    break;
//                } else {
////                    txtRead.append(new String(buffbyte), 0, flag);
//                    for(int i=0;i<=10;i++)
//                    {
//                        point2[i]=fos.read();
//                    }
//
//                }
//            }while (true);

            for(int i=0;i<=10;i++)
            {
                point2[i]=buffered.read();
            }

            buffered.close();

        }
        catch (Exception e)
        {
        e.printStackTrace();
        }
    }
}

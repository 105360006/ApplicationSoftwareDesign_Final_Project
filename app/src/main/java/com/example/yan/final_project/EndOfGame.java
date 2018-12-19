package com.example.yan.final_project;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;
        import android.widget.Toast;

        import java.io.BufferedInputStream;
        import java.io.BufferedReader;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileReader;
        import java.io.IOException;
        import java.io.BufferedWriter;
        import java.io.FileWriter;
        import java.io.FileOutputStream;
        import java.io.PrintWriter;
        import java.util.ArrayList;

public class EndOfGame extends AppCompatActivity
{
    int point[]=new int [11];
    int point2[]=new int [11];
    int pointTemp=0;
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

        for (int i=0;i<=10;i++)
        {
            point[i]=i*i*100;
        }

        pointTemp=pointTemp*pointTemp;
        showpoint.setText(""+pointTemp);


        pointRecord();
        pointRead();

    }

    public void goback(View v)
    {
        pointRead();
        txtRead.setText(point2[0]+"\n"+point2[1]+"\n"+point2[2]+"\n"+point2[3]+"\n"+point2[4]+"\n");
//        finish();
    }


    public void playagain(View v)
    {
        pointRecord();
//        Intent it = new Intent(this ,play_activity.class);
//        startActivityForResult(it,123);


    }

    public void pointRecord()
    {
        try
        {

            FileWriter fw= new FileWriter("test.txt");
            PrintWriter pw= new PrintWriter(new BufferedWriter(fw));
//            BufferedWriter bw=new BufferedWriter(fw);

            for (int i=0;i<=10;i++)
            {
                String a=point.toString();
                pw.println("ccc");

            }
            pw.println("ccc");


//            bw.close();
//            pw.close();

        }
        catch (IOException e)
        {
            txtRead.setText("wwwwww");
            e.printStackTrace();
        }

    }
    public void pointRead ()
    {
        try
        {
            BufferedReader  br=new  BufferedReader(new FileReader("test.txt"));

            for (int i=0;i<=10;i++)
            {

                point2[i]=Integer.parseInt(br.readLine())  ;

            }
//            point2[0]=Integer.parseInt(br.readLine()) ;
//            point2[1]=Integer.parseInt(br.readLine()) ;
//            point2[2]=Integer.parseInt(br.readLine()) ;
//            point2[3]=Integer.parseInt(br.readLine()) ;
//            point2[4]=Integer.parseInt(br.readLine()) ;
            br.close();
        }
        catch (IOException e)
        {
//            txtRead.setText("RRRRRR");
        }


    }

//    public void pointRecord(int[] p)
//    {
//        FileOutputStream fos = null;
////        try
//        {
//            String a="";
//            for (int i=0;i<=10;i++)
//            {
//                a= Integer.toString(p[i])+"\n";
//            }
//
//            fos.write(200);
//
//            fos = openFileOutput("pointRecord.txt", MODE_PRIVATE);
//
//            fos.close();
////            File file = new File(getFilesDir() + "/" + fos);
////            Toast.makeText(getApplicationContext(), file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//    private void pointReader()
//    {
//        FileInputStream fos = null;
//        BufferedInputStream buffered = null;
//        txtRead.setText("");
//        try
//        {
//            fos = openFileInput("pointRecord.txt");
//            buffered = new BufferedInputStream(fos);
//            byte[] buffbyte = new byte[200];
//            txtRead.setText("");
//            do {
//                int flag = buffered.read(buffbyte);
//                if (flag == -1)
//                {
//                    break;
//                } else {
//                    txtRead.append(new String(buffbyte), 0, flag);
//
//                }
//            }while (true);
//            buffered.close();
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

}

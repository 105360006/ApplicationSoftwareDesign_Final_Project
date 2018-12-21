package com.example.yan.final_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class rank extends AppCompatActivity
{
    int point[]=new int[11];

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        zeroRecord();
        reader();

        Data[] numData = new Data[9];
        int a=10;
        for (int i=0;i<numData.length;i++)
        {
            numData[i]=new Data();
            numData[i].showPoint=point[a]*point[a];
            a--;
        }

        numData[8].numPhoto=R.drawable.nine;
        numData[7].numPhoto=R.drawable.eight;
        numData[6].numPhoto=R.drawable.seven;
        numData[5].numPhoto=R.drawable.six;
        numData[4].numPhoto=R.drawable.five;
        numData[3].numPhoto=R.drawable.four;
        numData[2].numPhoto=R.drawable.three;
        numData[1].numPhoto=R.drawable.two;
        numData[0].numPhoto=R.drawable.one;

        myAdapter rankAdapter = new myAdapter(numData,R.layout.rank_list);
        ListView listView =(ListView) findViewById(R.id.rank);
        listView.setAdapter(rankAdapter);


    }
    public void goback(View v)
    {

        finish();
    }

    public void zeroRecord()
    {
        for(int i=0;i<=10;i++)
        {
            point[i]=0;
        }
    }


    class Data
    {
        int numPhoto;
        int showPoint;
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

    public class myAdapter extends BaseAdapter
    {
        private Data[] data ;
        private  int view;

        public myAdapter(Data[] data,int view)
        {
            this.data =data;
            this.view = view;
        }


        @Override
        public int getCount()
        {
            return data.length;
        }

        @Override
        public Data getItem(int position)
        {
            return data[position];
        }

        @Override
        public long getItemId(int position)
        {
            return 0;
        }

        @Override
        public View getView(int position, View rowView, ViewGroup parent)
        {
            rowView =getLayoutInflater().inflate(view,parent,false);

            TextView name =(TextView)rowView.findViewById(R.id.num);
            ImageView imageView =(ImageView)rowView.findViewById(R.id.imageView);
            name.setText(" "+data[position].showPoint);
            imageView.setImageResource(data[position].numPhoto);

            return rowView;
        }
    }
}

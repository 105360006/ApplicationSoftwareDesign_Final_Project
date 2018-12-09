package com.example.yan.final_project;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;

public class EndOfGame extends AppCompatActivity
{
    int point;
    TextView showpoint;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_of_game);

        showpoint=(TextView)findViewById(R.id.showpoint);

        Intent it =getIntent();
        Bundle bundle=it.getExtras();
        point=bundle.getInt("point");

        point=point*point;
        showpoint.setText(""+point);
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
}

package com.example.d_learn;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class user_course extends AppCompatActivity {


    CardView[] course=new CardView[4];
    ProgressBar b1,b2,b3,b4;
    TextView[] t=new TextView[4];
    TextView name;
    String[] courses;
    ImageView i1,i2,i3,i4;
    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    ImageView logout,profile;
    int[] progress=new int[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        setContentView(R.layout.activity_user_course);
        getSupportActionBar().hide();
        final String uid=getIntent().getStringExtra("uid");
        course[0]=findViewById(R.id.course1);
        course[1]=findViewById(R.id.course2);
        course[2]=findViewById(R.id.course3);
        course[3]=findViewById(R.id.course4);
        name=findViewById(R.id.textView17);
        b1=findViewById(R.id.progressBar1);
        b2=findViewById(R.id.progressBar2);
        b3=findViewById(R.id.progressBar3);
        b4=findViewById(R.id.progressBar4);
        t[0]=findViewById(R.id.textView1);
        t[1]=findViewById(R.id.textView2);
        t[2]=findViewById(R.id.textView3);
        t[3]=findViewById(R.id.textView4);
        i1=findViewById(R.id.imageView1);
        i2=findViewById(R.id.imageView2);
        i3=findViewById(R.id.imageView3);
        i4=findViewById(R.id.imageView4);


        for( int i=0; i<4;i++){
            final int r=i;
            course[i].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent in=new Intent(getApplicationContext(),user_topics.class);
                    in.putExtra("uid",uid);
                    in.putExtra("cname",t[r].getText().toString());
                    if(r==0)
                        in.putExtra("courseno","Course1");
                    if(r==1)
                        in.putExtra("courseno","Course2");
                    if(r==2)
                        in.putExtra("courseno","Course3");
                    if(r==3)
                        in.putExtra("courseno","Course4");
                    startActivity(in);
                }
            });
        }
        logout=findViewById(R.id.imageView16);
        profile=findViewById(R.id.imageView14);

        // init constraintLayout
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintlayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(2500);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2500);


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab=new AlertDialog.Builder(user_course.this);

                ab.setCancelable(false);
                ab.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent inte=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(inte);
                        finish();
                    }
                });
                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog a=ab.create();
                a.setTitle("Log out? ");
                a.show();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),user_profile.class);
                i.putExtra("uid",uid);
                startActivity(i);
            }
        });

        SQLiteDatabase mydatabase=openOrCreateDatabase("data.dat",MODE_PRIVATE,null);
        int userid=Integer.parseInt(uid);

        String sql="select * from courses where id="+userid+";";
        Cursor c=mydatabase.rawQuery(sql,null);
        c.moveToFirst();
        String dept=c.getString(1);
        progress[0]=Integer.parseInt(c.getString(2));
        progress[1]=Integer.parseInt(c.getString(3));
        progress[2]=Integer.parseInt(c.getString(4));
        progress[3]=Integer.parseInt(c.getString(5));
        if(dept.equals("1"))
        {
            courses=getResources().getStringArray(R.array.CSE);
            i1.setImageResource(R.drawable.python);
            i2.setImageResource(R.drawable.java);
            i3.setImageResource(R.drawable.web);
            i4.setImageResource(R.drawable.algorithm);
        }
        else if(dept.equals("2"))
        {
            courses=getResources().getStringArray(R.array.ECE);
            i1.setImageResource(R.drawable.magnet);
            i2.setImageResource(R.drawable.control);
            i3.setImageResource(R.drawable.comm);
            i4.setImageResource(R.drawable.electronic);
        }
        else if(dept.equals("3"))
        {
            courses=getResources().getStringArray(R.array.ME);
            i1.setImageResource(R.drawable.thermo);
            i2.setImageResource(R.drawable.design);
            i3.setImageResource(R.drawable.strength);
            i4.setImageResource(R.drawable.fluid);
        }
        t[0].setText(courses[0]);
        t[1].setText(courses[1]);
        t[2].setText(courses[2]);
        t[3].setText(courses[3]);
        b1.setProgress(progress[0]);
        b2.setProgress(progress[1]);
        b3.setProgress(progress[2]);
        b4.setProgress(progress[3]);
        String sql2="select * from user where id="+userid+";";
        c.close();
        Cursor cr=mydatabase.rawQuery(sql2,null);
        cr.moveToFirst();
        name.setText("Hi, "+cr.getString(0)+ " !");
        cr.close();



    }


    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }

    }

    // @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
    }


    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
            return;
        }
        else {
            Toast.makeText(getBaseContext(), "Press BACK again to Log out", Toast.LENGTH_SHORT).show();
        }

        mBackPressed = System.currentTimeMillis();
    }
}

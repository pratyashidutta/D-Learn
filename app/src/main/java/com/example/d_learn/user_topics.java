package com.example.d_learn;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class user_topics extends AppCompatActivity {


    CardView c[]=new CardView[10];
    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    ImageView logout,profile,study;
    ImageView[] img=new ImageView[10];
    TextView tv44;
    ProgressBar pr;
//    String[] dept;
    View back;
    int prog;
 //   String s1,s2,s3,s4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );
        setContentView(R.layout.activity_user_topics);
        getSupportActionBar().hide();
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        c[0]=findViewById(R.id.course1);
        c[1]=findViewById(R.id.course2);
        c[2]=findViewById(R.id.course3);
        c[3]=findViewById(R.id.course4);
        c[4]=findViewById(R.id.course5);
        c[5]=findViewById(R.id.course6);
        c[6]=findViewById(R.id.course7);
        c[7]=findViewById(R.id.course8);
        c[8]=findViewById(R.id.course9);
        c[9]=findViewById(R.id.course10);
        img[0]=findViewById(R.id.imageView1);
        img[1]=findViewById(R.id.imageView2);
        img[2]=findViewById(R.id.imageView3);
        img[3]=findViewById(R.id.imageView4);
        img[4]=findViewById(R.id.imageView5);
        img[5]=findViewById(R.id.imageView6);
        img[6]=findViewById(R.id.imageView7);
        img[7]=findViewById(R.id.imageView8);
        img[8]=findViewById(R.id.imageView9);
        img[9]=findViewById(R.id.imageView10);


       // c[10]=findViewById(R.id.course11);
        c[0].setEnabled(false);
        c[1].setEnabled(false);
        c[2].setEnabled(false);
        c[3].setEnabled(false);
        c[4].setEnabled(false);
        c[5].setEnabled(false);
        c[6].setEnabled(false);
        c[7].setEnabled(false);
        c[8].setEnabled(false);
        c[9].setEnabled(false);
       // c[10].setEnabled(false);
        final String uid=getIntent().getStringExtra("uid");
        final String course=getIntent().getStringExtra("cname");
        final String courseno=getIntent().getStringExtra("courseno");
        tv44=findViewById(R.id.textView44);
        logout=findViewById(R.id.imageView16);
        profile=findViewById(R.id.imageView14);
        tv44.setText(course);
        back=findViewById(R.id.view6);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        study=findViewById(R.id.imageView15);

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
                AlertDialog.Builder ab=new AlertDialog.Builder(user_topics.this);

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

        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),user_course.class);
                i.putExtra("uid",uid);
                startActivity(i);
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



        final SQLiteDatabase mydatabase=openOrCreateDatabase("data.dat",MODE_PRIVATE,null);
        Cursor cur=mydatabase.rawQuery("Select * from courses",null);
        cur.moveToFirst();
        while(!cur.isAfterLast())
        {
            if(cur.getString(0).equals(uid))
            {
//                dept=getResources().getStringArray(R.array.departments);
//                String udept=dept[cur.getInt(1)];
//
                if(courseno.equals("Course1"))
                {
                    prog=Integer.parseInt(cur.getString(2));
                  //  Toast.makeText(this, courseno, Toast.LENGTH_SHORT).show();
                }
                else if(courseno.equals("Course2"))
                {
                    prog=Integer.parseInt(cur.getString(3));
                }
                else if(courseno.equals("Course3"))
                {
                    prog=Integer.parseInt(cur.getString(4));
                }
                else if(courseno.equals("Course4"))
                {
                    prog=Integer.parseInt(cur.getString(5));
                }
            }
            cur.moveToNext();
        }

        pr=findViewById(R.id.progressBar5);
        pr.setProgress(prog);
        for(int i=0;i<10;i++){
//            if(!c[i].isEnabled())
//                c[i].setVisibility(c[i].GONE);
            img[i].setImageResource(R.drawable.hourglass);
        }

        for(int i=0;i<=prog;i++){
            if(i<10)
                c[i].setEnabled(true);
        }
        for(int i=0;i<prog;i++){
            img[i].setImageResource(R.drawable.checked);
        }
        for(int i=0;i<10;i++){
            if(!c[i].isEnabled())
                c[i].setVisibility(c[i].GONE);
           // img[i].setImageResource(R.drawable.hourglass);
        }


        final int userid=Integer.parseInt(uid);
        for(int r=0;r<10;r++)
        {
            final int i=r;
            c[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(i==9){
                        if(prog<10)
                            prog+=1;
                        if(courseno.equals("Course1"))
                        {
                            mydatabase.execSQL("update courses set course1="+prog+" where id="+userid+";");
                            Toast.makeText(user_topics.this, "updated", Toast.LENGTH_SHORT).show();
                        }
                        else if(courseno.equals("Course2"))
                        {
                            mydatabase.execSQL("update courses set course2="+prog+" where id="+userid+";");
                        }
                        else if(courseno.equals("Course3"))
                        {
                            mydatabase.execSQL("update courses set course3="+prog+" where id="+userid+";");
                        }
                        else if(courseno.equals("Course4"))
                        {
                            mydatabase.execSQL("update courses set course4="+prog+" where id="+userid+";");
                        }
                    }
                    else if(!c[i+1].isEnabled())
                    {
                        if(prog<10)
                            prog+=1;
                        if(i<9){
                            c[i+1].setEnabled(true);
                            c[i+1].setVisibility(c[i].VISIBLE);
                        }
                        if(courseno.equals("Course1"))
                        {
                            mydatabase.execSQL("update courses set course1="+prog+" where id="+userid+";");
                            Toast.makeText(user_topics.this, "updated", Toast.LENGTH_SHORT).show();
                        }
                        else if(courseno.equals("Course2"))
                        {
                            mydatabase.execSQL("update courses set course2="+prog+" where id="+userid+";");
                        }
                        else if(courseno.equals("Course3"))
                        {
                            mydatabase.execSQL("update courses set course3="+prog+" where id="+userid+";");
                        }
                        else if(courseno.equals("Course4"))
                        {
                            mydatabase.execSQL("update courses set course4="+prog+" where id="+userid+";");
                        }

                    }
                    Intent i=new Intent(getApplicationContext(),material.class);
                    i.putExtra("uid",uid);
                    i.putExtra("cname",course);
                    i.putExtra("courseno",courseno);
                    startActivity(i);
                }
            });
        }


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

    public void onBackPressed()
    {
       final String uid=getIntent().getStringExtra("uid");
       Intent i=new Intent(getApplicationContext(),user_course.class);
       i.putExtra("uid",uid);
       startActivity(i);
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}

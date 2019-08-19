package com.example.d_learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class signup4 extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;

    TextView tv9,tv11;
    Button b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        setContentView(R.layout.activity_signup4);
        getSupportActionBar().hide();

        b4=findViewById(R.id.button4);
        tv9=findViewById(R.id.textView9);
        tv11=findViewById(R.id.textView11);
        final String fname=getIntent().getStringExtra("fname");
        final String lname=getIntent().getStringExtra("lname");
        final String dob=getIntent().getStringExtra("dob");
        final String email=getIntent().getStringExtra("email");
        final String phone=getIntent().getStringExtra("phone");
        final String deptindex=getIntent().getStringExtra("deptindex");
        final String genderindex=getIntent().getStringExtra("genderindex");
        final String pass=getIntent().getStringExtra("pass");
        final String passhintqindex=getIntent().getStringExtra("passhintqindex");
        final String passhinta=getIntent().getStringExtra("passhinta");
        final String gender;
        if(genderindex.equals("0"))
            gender="M";
        else
            gender="F";



        // init constraintLayout
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintlayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(2500);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2500);


        tv9.setText("Hi "+fname+" !");

        SQLiteDatabase mydb=openOrCreateDatabase("data.dat", MODE_PRIVATE, null);
        int admintablerowcount=0;
        Cursor c=mydb.rawQuery("Select * from user", null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            admintablerowcount++;
            c.moveToNext();
        }
        if(admintablerowcount==0){
            mydb.execSQL("Insert into user Values('"+fname+"','"+lname+"','"+gender+"',1001,'"+dob+"','"+phone+"','"+email+"','"+pass+"','"+passhintqindex+"','"+passhinta+"','"+deptindex+"');");
            mydb.execSQL("insert into courses values(1001,'"+deptindex+"',0,0,0,0)");
        }
        else {
            mydb.execSQL("Insert into user(firstname,lastname,gender,dob,phone,email,pass,passhintque,passhintans,dept) Values('"+fname+"','"+lname+"','"+gender+"','"+dob+"','"+phone+"','"+email+"','"+pass+"','"+passhintqindex+"','"+passhinta+"','"+deptindex+"');");
            mydb.execSQL("insert into courses(dept,course1,course2,course3,course4) values('"+deptindex+"',0,0,0,0)");
        }
        c.close();

        Cursor cb=mydb.rawQuery("Select id from user ", null);
        cb.moveToLast();
        tv11.setText("Use User ID: "+cb.getString(0)+" to login");
        cb.close();


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),signin.class);
                startActivity(i);
            }
        });

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
            Toast.makeText(getBaseContext(), "Press again to go to Home Screen", Toast.LENGTH_SHORT).show();
        }

        mBackPressed = System.currentTimeMillis();
    }
}

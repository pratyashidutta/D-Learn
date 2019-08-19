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

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;

    Button sin,sup;
    TextView admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        admin=findViewById(R.id.textView23);
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),admin_login.class);
                startActivity(i);
            }
        });

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintlayout);


        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();


        animationDrawable.setEnterFadeDuration(2500);


        animationDrawable.setExitFadeDuration(2500);

        sin=findViewById(R.id.button);
        sup=findViewById(R.id.button2);
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),signin.class);
                startActivity(i);
            }
        });
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),signup1.class);
                startActivity(i);
            }
        });


        SQLiteDatabase mydb=openOrCreateDatabase("data.dat", MODE_PRIVATE, null);
        mydb.execSQL("CREATE TABLE if not exists user (\n" +
                "    firstname   VARCHAR,\n" +
                "    lastname    VARCHAR,\n" +
                "    gender      VARCHAR,\n" +
                "    id          INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    dob         VARCHAR,\n" +
                "    phone       VARCHAR,\n" +
                "    email       VARCHAR,\n" +
                "    pass        VARCHAR,\n" +
                "    passhintque VARCHAR,\n" +
                "    passhintans VARCHAR,\n" +
                "    dept        VARCHAR\n" +
                ");");
        mydb.execSQL("create table if not exists courses(id integer primary key autoincrement,dept varchar, course1 integer,course2 integer,course3 integer,course4 integer);");
        mydb.execSQL("create table if not exists admin(adminid varchar,pass varvhar)");
        int admintablerowcount=0;
        Cursor c=mydb.rawQuery("Select * from admin", null);
        c.moveToFirst();
        while(!c.isAfterLast()){
            admintablerowcount++;
            c.moveToNext();
        }
        c.close();
        if(admintablerowcount==0){
            mydb.execSQL("Insert into admin Values('admin','admin');");
           // mydb.execSQL("Create table if not exists users(userid Varchar Primary Key,pwd varchar,bal decimal);");
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

    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;



    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }
        else {
            Toast.makeText(getBaseContext(), "Press BACK again to exit", Toast.LENGTH_SHORT).show();
        }

        mBackPressed = System.currentTimeMillis();
    }
}

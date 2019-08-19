package com.example.d_learn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class user_profile extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    ImageView study,logout;
    TextView name,id,dob,phone,email,dept,cp,editname;
    String sname,sid,sphone,semail,sdept,sdob;
    String[] depart;
    View editemail,editphone,editdepart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        setContentView(R.layout.activity_user_profile);

        getSupportActionBar().hide();

        editname=findViewById(R.id.textView37);
        editemail=findViewById(R.id.view8);
        editphone=findViewById(R.id.view10);
        //editdepart=findViewById(R.id.view11);
        // init constraintLayout
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintlayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(2500);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2500);


        depart=getResources().getStringArray(R.array.departments);

        study=findViewById(R.id.imageView15);
        logout=findViewById(R.id.imageView16);
        final String uid=getIntent().getStringExtra("uid");
        cp=findViewById(R.id.textView30);
        cp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),change_user_pass.class);
                i.putExtra("uid",uid);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab=new AlertDialog.Builder(user_profile.this);

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

        editemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),editpro1.class);
                i.putExtra("ep","email");
                i.putExtra("uid",uid);
                startActivity(i);
            }
        });

        editphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),editpro1.class);
                i.putExtra("ep","phone");
                i.putExtra("uid",uid);
                startActivity(i);
            }
        });
        editname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),editpro1.class);
                i.putExtra("ep","name");
                i.putExtra("uid",uid);
                startActivity(i);
            }
        });

//        editdepart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(getApplicationContext(),editpro2.class);
//                i.putExtra("uid",uid);
//                startActivity(i);
//            }
//        });



//        int userid=Integer.parseInt(uid);
        name= findViewById(R.id.textView17);
        id= findViewById(R.id.textView21);
        email= findViewById(R.id.textView18);
        phone= findViewById(R.id.textView24);
        dept= findViewById(R.id.textView28);
        dob= findViewById(R.id.textView26);
        SQLiteDatabase mydatabase=openOrCreateDatabase("data.dat", MODE_PRIVATE, null);
        Cursor c=mydatabase.rawQuery("Select * from user where id='"+uid+"';", null);
        c.moveToFirst();
        sname="Hi, "+c.getString(0)+" "+c.getString(1)+ " !";
        name.setText(sname);
        sid=c.getString(3);
        id.setText(sid);
        semail=c.getString(6);
        email.setText(semail);
        sphone=c.getString(5);
        phone.setText(sphone);
        sdept=c.getString(10);
        dept.setText(depart[Integer.parseInt(sdept)]);
        sdob=c.getString(4);
        dob.setText(sdob);
        c.close();


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

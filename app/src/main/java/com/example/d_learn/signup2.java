package com.example.d_learn;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class signup2 extends AppCompatActivity {


    ImageView back,forward;
    // TextView signin;
     String[] depart;
     Spinner dept;
     ArrayAdapter<String> aa;
     EditText email,phone;
     int index;

    private RelativeLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        setContentView(R.layout.activity_signup2);



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


        final String fname=getIntent().getStringExtra("fname");
        final String lname=getIntent().getStringExtra("lname");
        final String dob=getIntent().getStringExtra("dob");
        final String genderindex=getIntent().getStringExtra("genderindex");

        email=findViewById(R.id.editText11);
        phone=findViewById(R.id.editText10);
       back=findViewById(R.id.imageView9);
       forward=findViewById(R.id.imageView10);
        dept=findViewById(R.id.spinner3);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });



        depart=getResources().getStringArray(R.array.departments);
        aa=new ArrayAdapter<String>(this, R.layout.spinner_xml,depart);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dept.setAdapter(aa);
        dept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                 index = arg0.getSelectedItemPosition();
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });




        // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        // init constraintLayout
        constraintLayout = (RelativeLayout) findViewById(R.id.constraintlayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(2500);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2500);




        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((email.getText().toString().equals("")) || (phone.getText().toString().equals("")) || (index == 0))
                    Toast.makeText(signup2.this, "Enter valid data", Toast.LENGTH_SHORT).show();
                if(!email.getText().toString().contains("@")||!email.getText().toString().contains(".com"))
                {
                    Toast.makeText(signup2.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                }

                else {
                    Intent i = new Intent(getApplicationContext(), signup3.class);
                    i.putExtra("fname",fname);
                    i.putExtra("lname",lname);
                    i.putExtra("dob",dob);
                    i.putExtra("email",email.getText().toString());
                    i.putExtra("phone",phone.getText().toString());
                    i.putExtra("deptindex",String.valueOf(index));
                    i.putExtra("genderindex",genderindex);
                    startActivity(i);
                }
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


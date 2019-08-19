package com.example.d_learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

public class signup3 extends AppCompatActivity {

    private RelativeLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    String[] passh;
    Spinner passhi;
    ArrayAdapter<String> aa;
    ImageView back;
    EditText pass,passcon,passhinta;
    Button register;
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );
        setContentView(R.layout.activity_signup3);
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

        pass=findViewById(R.id.editText8);
        passcon=findViewById(R.id.editText7);
        passhinta=findViewById(R.id.editText9);
        register=findViewById(R.id.button3);
        final String fname=getIntent().getStringExtra("fname");
        final String lname=getIntent().getStringExtra("lname");
        final String dob=getIntent().getStringExtra("dob");
        final String email=getIntent().getStringExtra("email");
        final String phone=getIntent().getStringExtra("phone");
        final String deptindex=getIntent().getStringExtra("deptindex");
        final String genderindex=getIntent().getStringExtra("genderindex");


        // getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        // init constraintLayout
        constraintLayout = (RelativeLayout) findViewById(R.id.constraintlayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(2500);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2500);

        passhi=findViewById(R.id.spinner2);
        passh=getResources().getStringArray(R.array.passhints);
        aa=new ArrayAdapter<String>(this, R.layout.spinner_xml,passh);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        passhi.setAdapter(aa);
        passhi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

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

        back=findViewById(R.id.imageView11);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((pass.getText().toString().equals(""))||(passhinta.getText().toString().equals(""))||(index==0)||!(pass.getText().toString().equals(passcon.getText().toString())))
                    Toast.makeText(signup3.this, "Invalid Data", Toast.LENGTH_SHORT).show();
                else {



                    Intent i=new Intent(getApplicationContext(),signup4.class);
                    i.putExtra("fname",fname);
                    i.putExtra("lname",lname);
                    i.putExtra("dob",dob);
                    i.putExtra("email",email);
                    i.putExtra("phone",phone);
                    i.putExtra("deptindex",deptindex);
                    i.putExtra("pass",pass.getText().toString());
                    i.putExtra("passhintqindex",String.valueOf(index));
                    i.putExtra("passhinta",passhinta.getText().toString());
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

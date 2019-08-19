package com.example.d_learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Spinner;
import android.widget.Toast;

public class forgotpass extends AppCompatActivity {

    private RelativeLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    ImageView i12;
    String[] passh;
    Spinner passhi;
    ArrayAdapter<String> aa;
    EditText pass,passcon,passhinta,uid;
    Button update;
    int index;
    String userid,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );
        setContentView(R.layout.activity_forgotpass);
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

        constraintLayout = (RelativeLayout) findViewById(R.id.constraintlayout);

        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        animationDrawable.setEnterFadeDuration(2500);

        animationDrawable.setExitFadeDuration(2500);
        pass=findViewById(R.id.editText13);
        passcon=findViewById(R.id.editText6);
        passhinta=findViewById(R.id.editText12);
        passhi=findViewById(R.id.spinner4);
        update=findViewById(R.id.button5);
        uid=findViewById(R.id.editText14);
        i12=findViewById(R.id.imageView12);
        i12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag=0;
                userid=uid.getText().toString();
                password=pass.getText().toString();
                SQLiteDatabase mydatabase=openOrCreateDatabase("data.dat",MODE_PRIVATE,null);
                Cursor c=mydatabase.rawQuery("Select * from user;",null);
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    if(c.getString(3).equals(userid) && c.getString(8).equals(String.valueOf(index)) && c.getString(9).equals(passhinta.getText().toString()) && password.equals(passcon.getText().toString()))
                    {
                        flag=1;
                        break;
                    }
                    c.moveToNext();
                }
                c.close();
                if(flag==1)
                {
                    mydatabase.execSQL("update user set pass='"+password+"'where id='"+userid+"';");
                    Toast.makeText(getApplicationContext(),"Password updated ", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enter Valid data",Toast.LENGTH_SHORT).show();
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

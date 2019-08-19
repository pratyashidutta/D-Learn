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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class admin_login extends AppCompatActivity {
    private RelativeLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    EditText aid,pass;
    Button login;
    String adminid,password;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

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

        getSupportActionBar().hide();



        constraintLayout = (RelativeLayout) findViewById(R.id.constraintlayout);


        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();


        animationDrawable.setEnterFadeDuration(2500);


        animationDrawable.setExitFadeDuration(2500);
        aid=findViewById(R.id.editText);
        pass=findViewById(R.id.editText2);
        aid.setText("");
        pass.setText("");
        login=findViewById(R.id.button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int flag=0;
                adminid=aid.getText().toString();
                password=pass.getText().toString();
                SQLiteDatabase mydatabase=openOrCreateDatabase("data.dat",MODE_PRIVATE,null);
                Cursor c=mydatabase.rawQuery("Select * from admin;",null);
                c.moveToFirst();
                while (!c.isAfterLast())
                {
                    if(c.getString(0).equals(adminid) && c.getString(1).equals(password))
                    {
                        flag=1;
                        break;
                    }
                    c.moveToNext();
                }
                c.close();
                if(flag==1)
                {
                    Toast.makeText(getApplicationContext(),"Login Successful", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),admin_page.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enter Valid Admin ID and Password!!!",Toast.LENGTH_SHORT).show();
                }

            }
        });
        back=findViewById(R.id.imageView4);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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

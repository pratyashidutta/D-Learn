package com.example.d_learn;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class editpro1 extends AppCompatActivity {

    TextView tv;
    EditText et;
    Button bt;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpro1);

        final SQLiteDatabase mydatabase=openOrCreateDatabase("data.dat",MODE_PRIVATE,null);

        final String change=getIntent().getStringExtra("ep");
        final String uid=getIntent().getStringExtra("uid");

        back=findViewById(R.id.imageView19);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        bt=findViewById(R.id.button14);
        tv=findViewById(R.id.textView31);
        et=findViewById(R.id.editText12);
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

        if(change.equals("name"))
        {
            tv.setText("Update Name");
            et.setHint("Enter Full Name");

        }
        else if(change.equals("phone"))
        {
            tv.setText("Update phone number");
            et.setHint("Enter Phone Number");
            //mydatabase.execSQL("Update  user set phone='"+et.getText().toString()+"' where uid="+Integer.parseInt(uid)+";");
        }

        else if(change.equals("email"))
        {
            tv.setText("Update E-mail");
            et.setHint("Enter Email-ID");
          //  mydatabase.execSQL("Update user set email='"+et.getText().toString()+"' where uid="+Integer.parseInt(uid)+";");
        }

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(change.equals("name"))
                {

                    String name=et.getText().toString();
                    int spi;
                    String fn,ln;
                    spi=name.indexOf(" ");
                    if(spi!=-1) {
                        fn = name.substring(0, spi);
                        ln = name.substring(spi + 1);
                        mydatabase.execSQL("Update user set firstname='" + fn + "',lastname='" + ln + "' where id='" + uid + "';");
                        // mydatabase.execSQL("Update user set lastname='"+ln+"' where id='"+uid+"';");
                        Toast.makeText(editpro1.this, "Name updated", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), user_profile.class);
                        i.putExtra("uid", uid);
                        startActivity(i);
                    }
                    else{
                        mydatabase.execSQL("Update user set firstname='" + name + "',lastname='' where id='" + uid + "';");
                        // mydatabase.execSQL("Update user set lastname='"+ln+"' where id='"+uid+"';");
                        Toast.makeText(editpro1.this, "Name updated", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), user_profile.class);
                        i.putExtra("uid", uid);
                        startActivity(i);
                    }
                }
                else if(change.equals("email"))
                {

                    mydatabase.execSQL("Update  user set email='"+et.getText().toString()+"' where id='"+uid+"';");
                    Toast.makeText(editpro1.this, "E-Mail updated", Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(getApplicationContext(),user_profile.class);
                    String userid=getIntent().getStringExtra("uid");
                    in.putExtra("uid",userid);
                    startActivity(in);
                }
                else if(change.equals("phone"))
                {

                    mydatabase.execSQL("Update  user set phone='"+et.getText().toString()+"' where id='"+uid+"';");
                    Toast.makeText(editpro1.this, "Phone number updated", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(),user_profile.class);
                    i.putExtra("uid",uid);
                    startActivity(i);
                }



            }
        });



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

package com.example.d_learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class admin_user_display extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    TextView tv1;
    String gender;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        setContentView(R.layout.activity_admin_user_display);

        back=findViewById(R.id.imageView22);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintlayout);


        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();


        animationDrawable.setEnterFadeDuration(2500);


        animationDrawable.setExitFadeDuration(2500);

        getSupportActionBar().hide();

        tv1=(TextView) findViewById(R.id.textView5);
        SQLiteDatabase mydatabase = openOrCreateDatabase("data.dat", MODE_PRIVATE, null);
        String data= "";
        Cursor c= mydatabase.rawQuery("Select * from user", null);
        c.moveToFirst();
        while(!c.isAfterLast())
        {
            if(c.getString(0).equals("M"))
            {
                gender="MALE";
            }
            else
            {
                gender="FEMALE";
            }
            data+="First Name:"+c.getString(0)+ "    "+"Last Name:"+c.getString(1)+ "     "+"Gender:"+c.getString(2) +"     "+"Id:"+c.getString(3)+ "     "+"DOB:"+c.getString(4)  +"     "+"Phone:"+c.getString(5)  +"    "+"Email:"+c.getString(6) + "     "+"Department:"+c.getString(10) +"\n\n";
            c.moveToNext();
        }
        tv1.setText(data);
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
}

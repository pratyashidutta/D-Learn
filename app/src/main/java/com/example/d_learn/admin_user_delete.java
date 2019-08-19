package com.example.d_learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class admin_user_delete extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    EditText et;
    Button b;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
        setContentView(R.layout.activity_admin_user_delete);
        getSupportActionBar().hide();

        back=findViewById(R.id.imageView21);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        et= findViewById(R.id.editText);
        b= findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String d= et.getText().toString();
                SQLiteDatabase mydatabase = openOrCreateDatabase("data.dat", MODE_PRIVATE, null);
                mydatabase.execSQL("DELETE FROM user WHERE id='"+d+"';");

                Toast.makeText(getApplicationContext(),d+" deleted Successfully",Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(),admin_page.class);
                startActivity(i);
            }
        });

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintlayout);


        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();


        animationDrawable.setEnterFadeDuration(2500);


        animationDrawable.setExitFadeDuration(2500);
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

package com.example.d_learn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class material extends YouTubeBaseActivity {

    YouTubePlayerView ytv1;
    LinearLayout l;
    CardView c,vid,pdf;
    ImageView im17;
    private YouTubePlayer mPlayer;
    private static final String API_KEY= "AIzaSyBNudDAzfzo7LEhITIvXyMy74C7j3SQ148";
    YouTubePlayer.OnInitializedListener onInitializedListener;
    View back;
    ImageButton yt;
    ImageView logout,profile,study;
    TextView subject;

    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);

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

        study=findViewById(R.id.imageView15);
        logout=findViewById(R.id.imageView16);
        profile=findViewById(R.id.imageView14);
        study.setImageResource(R.drawable.book);
        logout.setImageResource(R.drawable.logout);
        profile.setImageResource(R.drawable.user);

        ytv1=findViewById(R.id.view2);
        c=findViewById(R.id.playcard);
        vid=findViewById(R.id.card);
        l=findViewById(R.id.linear);
        pdf=findViewById(R.id.pdfcard);
        im17=findViewById(R.id.imageView17);
        im17.setImageResource(R.drawable.pdf);
        back=findViewById(R.id.view3);
        yt=findViewById(R.id.imageButton2);
        subject=findViewById(R.id.textView22);
        final String uid=getIntent().getStringExtra("uid");
        final String course=getIntent().getStringExtra("cname");
        final String courseno=getIntent().getStringExtra("courseno");

        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintlayout);


        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();


        animationDrawable.setEnterFadeDuration(2500);


        animationDrawable.setExitFadeDuration(2500);


        subject.setText(course);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playvideo();

            }

            public void playvideo()
            {
                c.setVisibility(c.GONE);
                vid.setVisibility(vid.VISIBLE);
                ytv1.initialize(API_KEY,new YouTubePlayer.OnInitializedListener() {
                    @Override

                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                        if (!b) {

                            mPlayer = youTubePlayer;
                            mPlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                            mPlayer.setFullscreen(false);
                            mPlayer.loadVideo("KitoxUB11go");
                            mPlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                                @Override
                                public void onFullscreen(boolean b) {
                                    mPlayer.setFullscreen(true);
                                }
                            });

                        }



                    }



                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    }

                });
            }

        });

        yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playvideo();

            }

            public void playvideo()
            {
                c.setVisibility(c.GONE);
                vid.setVisibility(vid.VISIBLE);
                ytv1.initialize(API_KEY,new YouTubePlayer.OnInitializedListener() {
                    @Override

                    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                        if (!b) {

                            mPlayer = youTubePlayer;
                            mPlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                            mPlayer.setFullscreen(false);
                            mPlayer.loadVideo("KitoxUB11go");
                            mPlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                                @Override
                                public void onFullscreen(boolean b) {
                                    mPlayer.setFullscreen(true);
                                }
                            });

                        }



                    }



                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                    }

                });
            }

        });

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),pdf.class);
                i.putExtra("uid",uid);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),user_topics.class);
                i.putExtra("uid",uid);
                i.putExtra("cname",course);
                i.putExtra("courseno",courseno);
                startActivity(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab=new AlertDialog.Builder(material.this);

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

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),user_profile.class);
                i.putExtra("uid",uid);
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
    @Override
    public void onBackPressed()
    {
        final String uid=getIntent().getStringExtra("uid");
        final String course=getIntent().getStringExtra("cname");
        final String courseno=getIntent().getStringExtra("courseno");
        Intent i=new Intent(getApplicationContext(),user_topics.class);
        i.putExtra("uid",uid);
        i.putExtra("cname",course);
        i.putExtra("courseno",courseno);
        startActivity(i);

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

package com.example.cookieclickerproject;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {


    ImageView imageView;
    ImageView imageView2;
    TextView textView;
    TextView textView2;
    Button button;
    int number;
    int coins;
    static AtomicInteger score;
    int random;
    ConstraintLayout constraintLayout;
    AnimationDrawable animationDrawable;
    TextView animateTextView;
    TextView animateTextView2;
    TextView sufficienttextview;
    TextView animateTextView4;
    TextView animateTextView5;
    TextView animateTextView6;
    TextView animateTextView7;
    TextView upgradetextview;
    int donutshopnumber = 0;
    ImageView imageViewdonut;
    TextView thestoretextview;

    ObjectAnimator textViewAnimator;


    private boolean checklayout;


    private ConstraintSet constraintSetOld = new ConstraintSet();

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        animationDrawable.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = new AtomicInteger(0);
        animateTextView = findViewById(R.id.textView6);
        animateTextView2 = findViewById(R.id.textView5);
        sufficienttextview = findViewById(R.id.textView4);
        animateTextView4 = findViewById(R.id.textView7);
        animateTextView5 = findViewById(R.id.textView8);
        animateTextView6 = findViewById(R.id.textView9);
        animateTextView7 = findViewById(R.id.textView10);
        upgradetextview = findViewById(R.id.textView11);
        thestoretextview=findViewById(R.id.textView2);
       imageViewdonut = (ImageView) findViewById(R.id.donutimage);
        constraintLayout = findViewById(R.id.id_layout);
        imageView = findViewById(R.id.image);
        textView2 = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        ImageView backgroundimageview= (ImageView)findViewById(R.id.backgroundimage);
        backgroundimageview.setBackgroundResource(R.drawable.animation);
        animationDrawable= (AnimationDrawable)backgroundimageview.getBackground();

        textView2.setTextColor(Color.WHITE);
        sufficienttextview .setTextColor(Color.WHITE);
        thestoretextview.setTextColor(Color.WHITE);
        upgradetextview.setTextColor(Color.WHITE);
        animateTextView.setTextColor(Color.WHITE);
        animateTextView2.setTextColor(Color.WHITE);
        animateTextView4.setTextColor(Color.WHITE);
        animateTextView5.setTextColor(Color.WHITE);
        animateTextView6.setTextColor(Color.WHITE);
        animateTextView7.setTextColor(Color.WHITE);



        CountingThread CountingThread = new CountingThread();
        CountingThread.start();
        final PassiveIncome PassiveIncome = new PassiveIncome();
        PassiveIncome.start();


        final ScaleAnimation animation = new ScaleAnimation(1.0f, 1.5f, 1.0f, 1.5f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f);
        animation.setDuration(250);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                coins++;
                v.startAnimation(animation);
               // textView.setText(" Number of Donut stores: " + donutshopnumber);
               // imageView2.setImageResource(R.drawable.donutshops);
                sufficienttextview.setText("Number of Clicks: " + number);


                //   constraintLayout.addView(imageViewdonut);


                if (number < 10) {
                    button.setEnabled(false);
                }

                if (number >= 10) {
                    button.setEnabled(true);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            upgradetextview.setText(" ");
                            donutshopnumber++;

                            imageViewdonut.animate().translationX(0).setDuration(3000);
                            imageViewdonut.animate().translationY(1340).setDuration(3000);

                            imageViewdonut.animate().alpha(0.4f).setDuration(2000);
                            score.getAndAdd(5);

                            number -=10;
                            constraintSetOld.applyTo(constraintLayout);

                                imageViewdonut = new ImageView(MainActivity.this);
                                imageViewdonut.setId(View.generateViewId());
                                imageViewdonut.setImageResource(R.drawable.donutshops);


                                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                                imageViewdonut.setLayoutParams(params);
                                constraintLayout.addView(imageViewdonut);
                                constraintSetOld.clone(constraintLayout);


                                constraintSetOld.connect(imageViewdonut.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
                                constraintSetOld.connect(imageViewdonut.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT);
                                constraintSetOld.connect(imageViewdonut.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);
                                constraintSetOld.connect(imageViewdonut.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);


                                constraintSetOld.setHorizontalBias(imageViewdonut.getId(), 0.1f);
                                constraintSetOld.setVerticalBias(imageViewdonut.getId(), 0.015f);

                             upgradetextview.setText("Donut Shop Number: "+donutshopnumber);



                        /*
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                                Path path = new Path();
                                path.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
                                ObjectAnimator animator = ObjectAnimator.ofFloat(imageView2, View.X, View.Y, path);
                                animator.ofArgb(Color.WHITE, Color.BLUE);
                                animator.setDuration(2000);
                                animator.start();

                                Path path2 = new Path();
                                path2.arcTo(0f, 0f, 1000f, 1000f, 270f, -180f, true);
                                ObjectAnimator animator2 = ObjectAnimator.ofFloat(textView, View.X, View.Y, path);
                                animator2.setDuration(2000);
                                animator2.start();
                            }
                           */


                        }

                    });
                }


                Random r = new Random();
                int randomnumber = r.nextInt(7 - 1) + 1;
                if (randomnumber == 1) {
                    animateTextView4.setText(" ");
                    textViewAnimator = ObjectAnimator.ofFloat(animateTextView4, "translationY", 0f, -1000f);
                    textViewAnimator.setDuration(2000);
                    textViewAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    textViewAnimator.start();
                    animateTextView4.setText("+1");

                } else if (randomnumber == 2) {
                    textViewAnimator = ObjectAnimator.ofFloat(animateTextView5, "translationY", 0f, -1000f);
                    textViewAnimator.setDuration(2000);
                    textViewAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    textViewAnimator.start();
                    animateTextView5.setText("+1");

                } else if (randomnumber == 3) {
                    textViewAnimator = ObjectAnimator.ofFloat(animateTextView6, "translationY", 0f, -1000f);
                    textViewAnimator.setDuration(2000);
                    textViewAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    textViewAnimator.start();
                    animateTextView6.setText("+1");

                } else if (randomnumber == 4) {
                    textViewAnimator = ObjectAnimator.ofFloat(animateTextView7, "translationY", 0f, -1000f);
                    textViewAnimator.setDuration(2000);
                    textViewAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    textViewAnimator.start();
                    animateTextView7.setText("+1");

                } else if (randomnumber == 5) {
                    textViewAnimator = ObjectAnimator.ofFloat(animateTextView, "translationY", 0f, -1000f);
                    textViewAnimator.setDuration(2000);
                    textViewAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    textViewAnimator.start();

                } else if (randomnumber == 6) {
                    textViewAnimator = ObjectAnimator.ofFloat(animateTextView2, "translationY", 500f, -1000f);
                    textViewAnimator.setDuration(2000);
                    textViewAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                    textViewAnimator.start();
                    animateTextView2.setText("+1");

                }


            }


        });

    }

    public class CountingThread extends Thread {
        @Override

        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);

                } catch (Exception e) {
                }
                score.getAndAdd(1);
                textView2.post(new Runnable() {
                    @Override
                    public void run() {
                        textView2.setText("Score: " + score.get());
                    }
                });
            }
        }
    }


    public class PassiveIncome extends Thread {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        public void run() {
            while (true) {
                try {
                    sleep(1000);

                } catch (Exception e) {}
                    constraintLayout.post(new Runnable() {
                        @Override
                        public void run() {

                            if(score.get() >= 10 && imageViewdonut == null)

                imageViewdonut = new ImageView(MainActivity.this);
                imageViewdonut.setId(View.generateViewId());
                imageViewdonut.setImageResource(R.drawable.donutshops);


                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
                imageViewdonut.setLayoutParams(params);
                //constraintLayout.addView(imageViewdonut);
                constraintSetOld.clone(constraintLayout);


                constraintSetOld.connect(imageViewdonut.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
                constraintSetOld.connect(imageViewdonut.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT);
                constraintSetOld.connect(imageViewdonut.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);
                constraintSetOld.connect(imageViewdonut.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT);


                constraintSetOld.setHorizontalBias(imageViewdonut.getId(), 0.1f);
                constraintSetOld.setVerticalBias(imageViewdonut.getId(), 0.015f);
            }

                    });
        }
    }
}
}















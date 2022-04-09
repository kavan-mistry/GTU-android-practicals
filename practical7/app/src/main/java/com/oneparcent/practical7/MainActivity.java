package com.oneparcent.practical7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Ball ball;
    Spinner spnRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnRadius = findViewById(R.id.radius);
        ball = findViewById(R.id.ball);
        spnRadius.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ball.clearAnimation();
                animate(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void animate(int position){
        RotateAnimation rotate = new RotateAnimation(0f, 360f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(2000);
        rotate.setRepeatCount(0);
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (position == 0) {
                    ball.radius = 50;
                    ball.colorHex = "#ffe700";
                    ball.invalidate();
                } else if (position == 1) {
                    ball.radius = 100;
                    ball.colorHex = "#8a2be2";
                    ball.invalidate();
                } else if (position == 2) {
                    ball.radius = 200;
                    ball.colorHex = "#66cdaa";
                    ball.invalidate();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        ball.startAnimation(rotate);

    }
}
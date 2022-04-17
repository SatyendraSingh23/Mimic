package com.raven.remoteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ChoiceActivity extends AppCompatActivity {
    public ImageButton scanChoiceButton;
    public ImageButton codeChoiceButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        findViews();
        setListeners();
    }

    private void findViews() {
        scanChoiceButton=findViewById(R.id.scanChoiceButton);
        codeChoiceButton=findViewById(R.id.codeChoiceButton);
        Animation openupAnimation;
        openupAnimation = AnimationUtils.loadAnimation(this,R.anim.textopenup);
        openupAnimation.setDuration(400);
        scanChoiceButton.startAnimation(openupAnimation);
        codeChoiceButton.startAnimation(openupAnimation);
    }
    private void setListeners()
    {


        this.scanChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation blinkAnimation;
                blinkAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_anim);
                scanChoiceButton.startAnimation(blinkAnimation);
                Intent intent;
                intent = new Intent(ChoiceActivity.this, ScanActivity.class);
                startActivity(intent);

            }
        });
        this.codeChoiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation blinkAnimation;
                blinkAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink_anim);
                codeChoiceButton.startAnimation(blinkAnimation);
                Intent intent;
                intent = new Intent(ChoiceActivity.this,MainActivity3.class);
                startActivity(intent);
            }
        });

    }
}
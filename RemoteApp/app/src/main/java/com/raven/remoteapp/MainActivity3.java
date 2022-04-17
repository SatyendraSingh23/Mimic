package com.raven.remoteapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.raven.Sender.Sender;

public class MainActivity3 extends AppCompatActivity {
    public ImageView ipLineImage;
    public TextView errorTv;
    public ImageView submitTv;
    public EditText ipInput;
    Sender sender=Sender.getInstance();
    private String Ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        findAllElement();
        setListeners();
    }

    @SuppressLint("SetTextI18n")
    private void setListeners()
    {
            this.submitTv.setOnClickListener(view -> {
                MainActivity3.this.errorTv.setText("");
                MainActivity3.this.Ip=MainActivity3.this.ipInput.getText().toString().trim();
                if (!MainActivity3.this.Ip.isEmpty()) {

                    sender.Connect(MainActivity3.this.Ip);
                    if (!sender.isConnectionSuccessfull()) {
                        MainActivity3.this.errorTv.setText("Oops! code didn't matched.");
                    }else {
                        MainActivity3.this.errorTv.setText("");
                        Intent intent = new Intent(MainActivity3.this, EntryActivity.class);
                        startActivity(intent);
                    }
                }
            });

        //------------------------------------------------------------------------------------------//
        Animation openUpAnimation=AnimationUtils.loadAnimation(this,R.anim.openup);
        openUpAnimation.setDuration(200);
        openUpAnimation.setRepeatCount(1);
        Animation textOpenUpAnimation=AnimationUtils.loadAnimation(this,R.anim.textopenup);
        textOpenUpAnimation.setDuration(800);
        ipLineImage.startAnimation(textOpenUpAnimation);
        ipInput.startAnimation(textOpenUpAnimation);
        //------------------------------------------------------------------------------------------//
    }

    private void findAllElement() {
        this.errorTv=findViewById(R.id.errorTv);
        this.ipLineImage=findViewById(R.id.ipLineBg);
        this.submitTv=findViewById(R.id.codeChoiceButton);
        this.ipInput=findViewById(R.id.ipInputText);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
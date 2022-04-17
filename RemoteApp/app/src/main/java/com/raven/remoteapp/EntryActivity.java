package com.raven.remoteapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.raven.Sender.Sender;

public class EntryActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener {
    public boolean isDown=false;
    public View pad,padBackground;
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public FrameLayout frameLayout;
    public int height,width,oldHeight,oldWidth;
    public Sender sd;
    public float x,y;
    public long lastClickTime=0;
    public Button leftClick,midClick,rightClick;
    public Switch btnSwitch;
    public ConstraintLayout constraintLayout;
    public ConstraintSet constraintSet;
    public ConstraintLayout.LayoutParams oldLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //----------------------------------------------------
        frameLayout=findViewById(R.id.main_framelayout);
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.navigation);
        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //-----------------------------------------------------
        constraintLayout=findViewById(R.id.constraintLayout);
        pad=findViewById(R.id.padView);
        leftClick=findViewById(R.id.leftBtn);
        padBackground=findViewById(R.id.padView2);
        midClick=findViewById(R.id.midBtn);
        rightClick=findViewById(R.id.rightBtn);
        btnSwitch=findViewById(R.id.btnSwitch);

        constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

    }

    public void restoreUI()
    {
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(constraintLayout.getLayoutParams());
        constraintSet.connect(padBackground.getId(),ConstraintSet.BOTTOM,leftClick.getId(),ConstraintSet.TOP);
        constraintLayout.removeView(padBackground);
        constraintLayout.removeView(pad);
        layoutParams.height=constraintLayout.getHeight()-leftClick.getHeight()-40;
        layoutParams.width=ConstraintLayout.LayoutParams.MATCH_PARENT;
        padBackground.setLayoutParams(layoutParams);
        constraintLayout.addView(padBackground);

        ConstraintLayout.LayoutParams padParams = new ConstraintLayout.LayoutParams(padBackground.getLayoutParams());
        constraintSet.connect(pad.getId(),ConstraintSet.BOTTOM,padBackground.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(pad.getId(),ConstraintSet.START,padBackground.getId(),ConstraintSet.START);
        constraintSet.connect(pad.getId(),ConstraintSet.TOP,padBackground.getId(),ConstraintSet.TOP);
        constraintSet.connect(pad.getId(),ConstraintSet.END,padBackground.getId(),ConstraintSet.END);
        constraintSet.setHorizontalBias(pad.getId(), (float) 0.486);
        padParams.height=ConstraintLayout.LayoutParams.MATCH_PARENT;
        padParams.width=ConstraintLayout.LayoutParams.MATCH_PARENT;
        padParams.setMargins((int)(width*0.053),(int)(height*0.150),(int)( width*0.055),(int)(height*0.380));

        pad.setLayoutParams(padParams);
        constraintLayout.addView(pad);

        leftClick.setHeight(49);
        leftClick.setWidth(271);
        layoutParams.setMarginStart(3);
        constraintSet.connect(leftClick.getId(),ConstraintSet.START,padBackground.getId(),ConstraintSet.START);
        constraintSet.connect(leftClick.getId(),ConstraintSet.BOTTOM,constraintLayout.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(leftClick.getId(),ConstraintSet.END,padBackground.getId(),ConstraintSet.END);
        constraintSet.connect(leftClick.getId(),ConstraintSet.TOP,padBackground.getId(),ConstraintSet.TOP);
        constraintSet.setHorizontalBias(leftClick.getId(), (float) 0.05);
        constraintSet.setVerticalBias(leftClick.getId(),(float)0.0);
        constraintLayout.addView(leftClick);

        midClick.setHeight(49);
        midClick.setWidth(110);
        layoutParams.setMarginStart(3);
        constraintSet.connect(midClick.getId(),ConstraintSet.START,leftClick.getId(),ConstraintSet.START);
        constraintSet.connect(midClick.getId(),ConstraintSet.BOTTOM,constraintLayout.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(midClick.getId(),ConstraintSet.END,rightClick.getId(),ConstraintSet.END);
        constraintSet.connect(midClick.getId(),ConstraintSet.TOP,padBackground.getId(),ConstraintSet.TOP);
        constraintLayout.addView(midClick);
        
        rightClick.setHeight(49);
        rightClick.setWidth(271);
        layoutParams.setMarginStart(3);
        constraintSet.connect(rightClick.getId(),ConstraintSet.START,pad.getId(),ConstraintSet.START);
        constraintSet.connect(rightClick.getId(),ConstraintSet.BOTTOM,constraintLayout.getId(),ConstraintSet.BOTTOM);
        constraintSet.connect(rightClick.getId(),ConstraintSet.END,padBackground.getId(),ConstraintSet.END);
        constraintSet.connect(rightClick.getId(),ConstraintSet.TOP,padBackground.getId(),ConstraintSet.TOP);
        constraintLayout.addView(rightClick);

        height=oldHeight;
        width=oldWidth;
        sd.send(height+"@"+width);

    }
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        oldHeight=pad.getHeight();
        oldWidth=pad.getWidth();
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(constraintLayout.getLayoutParams());
        ConstraintLayout.LayoutParams padParams=new ConstraintLayout.LayoutParams(pad.getLayoutParams());
        oldLayoutParams=padParams;
        //-----------------------------------
        Animation zoominAnimation,leftAnimation,rightAnimation;
        zoominAnimation = AnimationUtils.loadAnimation(this,R.anim.zoomin);
        zoominAnimation.setDuration(1500);
        padBackground.startAnimation(zoominAnimation);
        pad.startAnimation(zoominAnimation);
        btnSwitch.startAnimation(zoominAnimation);
        leftAnimation=AnimationUtils.loadAnimation(this,R.anim.lefttoright);
        leftAnimation.setDuration(1000);
        leftClick.startAnimation(leftAnimation);
        rightAnimation=AnimationUtils.loadAnimation(this,R.anim.righttoleft);
        rightAnimation.setDuration(1000);
        rightClick.startAnimation(rightAnimation);

        midClick.startAnimation(AnimationUtils.loadAnimation(this,R.anim.bottomtoup));

        height=pad.getHeight();
        width=pad.getWidth();
        sd=Sender.getInstance();
        sd.sendDimension(height+"@"+width);

        btnSwitch.bringToFront();
        //--------------------------------------------
        pad.setOnTouchListener((view, event) -> {
            int pointerIndex = event.getActionIndex();
            if(event.getActionMasked()== MotionEvent.ACTION_UP)
            {
                if(pointerIndex==0) sd.send("mu");
            }
            if (event.getAction()==MotionEvent.ACTION_DOWN)
            {
                long clickTime = System.currentTimeMillis();
                if (clickTime - lastClickTime < 300)
                {
                    sd.send("lcd");
                }
                lastClickTime = clickTime;

            }
            if(event.getActionMasked()== MotionEvent.ACTION_MOVE ) {
                x=event.getX();
                y=event.getY();
//                if(event.getPointerCount()>1) {
//                    if(isDown && ((int)y%200==0))
//                    {
//
//                        sd.send("lcd");
//                        sd.send("lcu");
//                        isDown=false;
//                    }
//                    sd.send("drag," + y);
//                }
                if(x>0 && x<width && y>0 && y<height) sd.send(x+","+y);
            }
            if(event.getActionMasked()==MotionEvent.ACTION_POINTER_DOWN && pointerIndex==1) {
                sd.send("rcd");
                sd.send("rcu");
                isDown=true;
            }
            return true;
        });

        leftClick.setOnTouchListener((view, event) -> {
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("lcu");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("lcd");
            return true;
        });
        rightClick.setOnTouchListener((view, event) -> {
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("rcu");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("rcd");
            return true;
        });
        midClick.setOnTouchListener((view, event) -> {
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("mcu");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("mcd");
            return false;
        });
        //-----------------------------------------------------
        btnSwitch.setOnCheckedChangeListener((compoundButton, b) -> {

            if (!b)
            {
                restoreUI();
            }
            else
            {
                constraintLayout.removeView(leftClick);
                constraintLayout.removeView(rightClick);
                constraintLayout.removeView(midClick);
                constraintLayout.removeView(pad);
                layoutParams.height= ConstraintLayout.LayoutParams.MATCH_PARENT;
                layoutParams.width=ConstraintLayout.LayoutParams.MATCH_PARENT;
                padBackground.setLayoutParams(layoutParams);

                padParams.height=ConstraintLayout.LayoutParams.MATCH_PARENT;
                padParams.width=ConstraintLayout.LayoutParams.MATCH_PARENT;
                padParams.setMargins((int)(width*0.053),(int)(height*0.175),(int)( width*0.055),(int)(height*0.150));
                pad.setLayoutParams(padParams);
                constraintLayout.addView(pad);

                height=pad.getHeight();
                width=pad.getWidth();
                sd.send(height+"@"+width);
            }
            btnSwitch.bringToFront();
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sd.exit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int menu_id=item.getItemId();
        Intent i1;
        if(menu_id==R.id.navkeyboard)
        {
            i1 = new Intent(EntryActivity.this,MainActivity2.class);
            startActivity(i1);
        }
        if(menu_id==R.id.pptMode)
        {
            i1 = new Intent(EntryActivity.this,presentationActivity.class);
            startActivity(i1);
        }
        if(menu_id==R.id.logout)
        {
            i1 = new Intent(EntryActivity.this,MainActivity.class);
            sd.exit();
            startActivity(i1);

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        sd.exit();
        finish();
    }
}

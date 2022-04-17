package com.raven.remoteapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.raven.Sender.Sender;



public class MainActivity2 extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public FrameLayout frameLayout;
    public Sender sd;
    public Button escBtn,f1Btn,f2Btn,f3Btn,f4Btn,f5Btn,f6Btn,f7Btn,f8Btn,f9Btn,f10Btn,f11Btn,f12Btn,prtBtn,delBtn;
    public Button tldBtn,num1Btn,num2Btn,num3Btn,num4Btn,num5Btn,num6Btn,num7Btn,num8Btn,num9Btn,num0Btn,minBtn,eqBtn,backBtn;
    public Button tabBtn,qBtn,wBtn,eBtn,rBtn,tBtn,yBtn,uBtn,iBtn,oBtn,pBtn,boBtn,bcBtn,fsBtn;
    public Button capsBtn,aBtn,sBtn,dBtn,fBtn,gBtn,hBtn,jBtn,kBtn,lBtn,scBtn,apsBtn,etrBtn;
    public Button shift1Btn,zBtn,xBtn,cBtn,vBtn,bBtn,nBtn,mBtn,commaBtn,dotBtn,bsBtn,shift2Btn;
    public Button ctrl1Btn,winBtn,alt1Btn,spaceBtn,alt2Btn,ctrl2Btn,pupKey,pdnKey;
    public Button upBtn,leftBtn,downBtn,rightBtn,insBtn;

    public Animation blinkAnimation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.findViews();
        this.initButtons();
        blinkAnimation = AnimationUtils.loadAnimation(this, R.anim.blink_anim);
        blinkAnimation.setDuration(10);

        sd=Sender.getInstance();
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
    }
    void findViews()
    {
//        this.mouseBtn=findViewById(R.id.mouseKey);
        //-------- FIRST ROW ---------//
        this.escBtn=findViewById(R.id.escKey);
        this.f1Btn=findViewById(R.id.f1Key);
        this.f2Btn=findViewById(R.id.f2Key);
        this.f3Btn=findViewById(R.id.f3Key);
        this.f4Btn=findViewById(R.id.f4Key);
        this.f5Btn=findViewById(R.id.f5Key);
        this.f6Btn=findViewById(R.id.f6Key);
        this.f7Btn=findViewById(R.id.f7Key);
        this.f8Btn=findViewById(R.id.f8Key);
        this.f9Btn=findViewById(R.id.f9Key);
        this.f10Btn=findViewById(R.id.f10Key);
        this.f11Btn=findViewById(R.id.f11Key);
        this.f12Btn=findViewById(R.id.f12Key);
        this.prtBtn=findViewById(R.id.prtKey);
        this.delBtn=findViewById(R.id.delKey);
        // ------- SECOND ROW ----------//
        this.tldBtn=findViewById(R.id.tldKey);
        this.num1Btn=findViewById(R.id.num1Key);
        this.num2Btn=findViewById(R.id.num2Key);
        this.num3Btn=findViewById(R.id.num3Key);
        this.num4Btn=findViewById(R.id.num4Key);
        this.num5Btn=findViewById(R.id.num5Key);
        this.num6Btn=findViewById(R.id.num6Key);
        this.num7Btn=findViewById(R.id.num7Key);
        this.num8Btn=findViewById(R.id.num8Key);
        this.num9Btn=findViewById(R.id.num9Key);
        this.num0Btn=findViewById(R.id.num0Key);
        this.minBtn=findViewById(R.id.minKey);
        this.eqBtn=findViewById(R.id.eqKey);
        this.backBtn=findViewById(R.id.backKey);
        // ------- THIRD ROW ----------//
        this.tabBtn=findViewById(R.id.tabKey);
        this.qBtn=findViewById(R.id.qKey);
        this.wBtn=findViewById(R.id.wKey);
        this.eBtn=findViewById(R.id.eKey);
        this.rBtn=findViewById(R.id.rKey);
        this.tBtn=findViewById(R.id.tKey);
        this.yBtn=findViewById(R.id.yKey);
        this.uBtn=findViewById(R.id.uKey);
        this.iBtn=findViewById(R.id.iKey);
        this.oBtn=findViewById(R.id.oKey);
        this.pBtn=findViewById(R.id.pKey);
        this.boBtn=findViewById(R.id.boKey);
        this.bcBtn=findViewById(R.id.bcKey);
        this.fsBtn=findViewById(R.id.fsKey);
        // ------- FOURTH ROW ----------//
        this.capsBtn=findViewById(R.id.capsKey);
        this.aBtn=findViewById(R.id.aKey);
        this.sBtn=findViewById(R.id.sKey);
        this.dBtn=findViewById(R.id.dKey);
        this.fBtn=findViewById(R.id.fKey);
        this.gBtn=findViewById(R.id.gKey);
        this.hBtn=findViewById(R.id.hKey);
        this.jBtn=findViewById(R.id.jKey);
        this.kBtn=findViewById(R.id.kKey);
        this.lBtn=findViewById(R.id.lKey);
        this.scBtn=findViewById(R.id.scKey);
        this.apsBtn=findViewById(R.id.apsKey);
        this.etrBtn=findViewById(R.id.etrKey);
        // ------- FIFTH ROW ----------//
        this.shift1Btn=findViewById(R.id.sft1Key);
        this.zBtn=findViewById(R.id.zKey);
        this.xBtn=findViewById(R.id.xKey);
        this.cBtn=findViewById(R.id.cKey);
        this.vBtn=findViewById(R.id.vKey);
        this.bBtn=findViewById(R.id.bKey);
        this.nBtn=findViewById(R.id.nKey);
        this.mBtn=findViewById(R.id.mKey);
        this.commaBtn=findViewById(R.id.commaKey);
        this.dotBtn=findViewById(R.id.dotKey);
        this.bsBtn=findViewById(R.id.bsKey);
        this.shift2Btn=findViewById(R.id.sft2Key);
        //--------- SIXTH KEY -------------//
        this.ctrl1Btn=findViewById(R.id.ctr1Key);
        this.winBtn=findViewById(R.id.winKey);
        this.alt1Btn=findViewById(R.id.alt1Key);
        this.spaceBtn=findViewById(R.id.spaceKey);
        this.alt2Btn=findViewById(R.id.alt2Key);
        this.ctrl2Btn=findViewById(R.id.ctrl2Key);
        this.pupKey=findViewById(R.id.pupKey);
        this.pdnKey=findViewById(R.id.pdnKey);
        //--------- SEVENTH KEY -------------//
        this.upBtn=findViewById(R.id.upKey);
        this.leftBtn=findViewById(R.id.leftKey);
        this.downBtn=findViewById(R.id.downKey);
        this.rightBtn=findViewById(R.id.rightKey);
        this.insBtn=findViewById(R.id.insKey);
    }
    @SuppressLint("ClickableViewAccessibility")
    void initButtons()
    {

        //----------  FIRST  ROW  --------------//
        this.escBtn.setOnTouchListener((view, event) -> {
            this.escBtn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-esc");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("esc");
            return false;
        });
        this.f1Btn.setOnTouchListener((view, event) -> {
            this.f1Btn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-f1");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("f1");
            return false;
        });
        this.f2Btn.setOnTouchListener((view, event) -> {
            this.f2Btn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-f2");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("f2");
            return false;
        });
        this.f3Btn.setOnTouchListener((view, event) -> {
            this.f3Btn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-f3");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("f3");
            return false;
        });
        this.f4Btn.setOnTouchListener((view, event) -> {
            this.f4Btn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-f4");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("f4");
            return false;
        });
        this.f5Btn.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                f5Btn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-f5");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("f5");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("f5");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.f6Btn.setOnTouchListener((view, event) -> {
            f6Btn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-f6");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("f6");
            return false;
        });
        this.f7Btn.setOnTouchListener((view, event) -> {
            f7Btn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-f7");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("f7");
            return false;
        });
        this.f8Btn.setOnTouchListener((view, event) -> {
            f8Btn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-f8");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("f8");
            return false;
        });
        this.f9Btn.setOnTouchListener((view, event) -> {
            f9Btn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-f9");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("f9");
            return false;
        });
        this.f10Btn.setOnTouchListener((view, event) -> {
            f10Btn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-f10");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("f10");
            return false;
        });
        this.f11Btn.setOnTouchListener((view, event) -> {
            f11Btn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-f11");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("f11");
            return false;
        });
        this.f12Btn.setOnTouchListener((view, event) -> {
            f12Btn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-f12");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("f12");
            return false;
        });
        this.prtBtn.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                prtBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-ptr");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("ptr");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("ptr");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.delBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                delBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("del");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("del");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("del");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        //------------- SECOND ROW ------------//
        this.tldBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                tldBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-`");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("`");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("`");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.num1Btn.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                num1Btn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-1");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("1");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("1");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.num2Btn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                num2Btn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-2");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("2");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("2");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.num3Btn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                num3Btn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-3");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("3");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("3");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.num4Btn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                num4Btn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-4");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("4");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("4");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.num5Btn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                num5Btn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-5");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("5");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("5");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.num6Btn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                num6Btn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-6");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("6");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("6");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.num7Btn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                num7Btn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-7");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("7");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("7");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.num8Btn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                num8Btn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-8");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("8");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("8");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.num9Btn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                num9Btn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-9");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("9");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("9");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.num0Btn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                num0Btn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-0");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("0");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("0");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.minBtn.setOnTouchListener(new View.OnTouchListener() {

            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                minBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U--");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("-");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("-");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.eqBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                eqBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-=");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("=");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("=");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.backBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                backBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-back");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("back");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("back");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        //------------- THIRD ROW --------------------//
        this.tabBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                tabBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-tab");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("tab");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("tab");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.qBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                qBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-q");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("q");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("q");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.wBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                wBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-w");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("w");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("w");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.eBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP){
                    eBtn.startAnimation(blinkAnimation);
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-e");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("e");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("e");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.rBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                rBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-r");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("r");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("r");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.tBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                tBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-t");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("t");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("t");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.yBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                yBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-y");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("y");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("y");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.uBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                uBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-u");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("u");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("u");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.iBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                iBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-i");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("i");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("i");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.oBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                oBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-o");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("o");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("o");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.pBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                pBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-p");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("p");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("p");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.boBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                boBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-[");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("[");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("[");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.bcBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                bcBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-]");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("]");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("]");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.fsBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                fsBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-\\");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("\\");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("\\");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        //------------- FOURTH ROW ------------------//
        this.capsBtn.setOnTouchListener((view, event) -> {
            capsBtn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-caps");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("caps");
            return false;

        });
        this.aBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                aBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-a");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("a");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("a");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.sBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                sBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-s");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("s");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {

                    sd.send("s");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.dBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                dBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-d");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("d");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("d");
                    mHandler.postDelayed(this, 50);
                }
            };

        });
        this.fBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                fBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-f");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("f");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("f");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.gBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                gBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-g");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("g");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("g");
                    mHandler.postDelayed(this, 50);
                }
            };

        });
        this.hBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                hBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-h");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("h");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("h");
                    mHandler.postDelayed(this, 50);
                }
            };

        });
        this.jBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                jBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-j");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("j");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("j");
                    mHandler.postDelayed(this, 50);
                }
            };

        });
        this.kBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                kBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-k");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("k");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("k");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.lBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                lBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-l");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("l");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("l");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.scBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                scBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-;");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send(";");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send(";");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.apsBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                apsBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-'");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("'");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("'");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.etrBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                etrBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-enter");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("enter");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("enter");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        //------------ FIFTH ROW --------------//
        this.shift1Btn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                shift1Btn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-shift");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("shift");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("shift");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.zBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                zBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-z");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("z");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("z");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.xBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                xBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-x");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("x");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("x");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.cBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                cBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-c");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("c");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("c");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.vBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                vBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-v");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("v");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("v");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.bBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                bBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-b");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("b");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("b");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.nBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                nBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-n");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("n");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("n");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.mBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                mBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-m");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("m");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("m");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.commaBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                commaBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-COMMA");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("COMMA");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("COMMA");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.dotBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                dotBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-.");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send(".");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send(".");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.bsBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                bsBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-/");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("/");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("/");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.shift2Btn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                shift2Btn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-shift");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("shift");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("shift");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        //------------------- FIFTH  ROW -----------------//
        this.ctrl1Btn.setOnTouchListener((view, event) -> {
            ctrl1Btn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-ctrl");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("ctrl");
            return false;

        });
        this.winBtn.setOnTouchListener((view, event) -> {
            winBtn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-win");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("win");
            return false;

        });
        this.alt1Btn.setOnTouchListener((view, event) -> {
            alt1Btn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-alt");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("alt");
            return false;

        });
        this.spaceBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                spaceBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U- ");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send(" ");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send(" ");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.alt2Btn.setOnTouchListener((view, event) -> {
            alt2Btn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-alt");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("alt");
            return false;

        });
        this.ctrl2Btn.setOnTouchListener((view, event) -> {
            ctrl2Btn.startAnimation(blinkAnimation);
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-ctrl");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("ctrl");
            return false;

        });
        this.pupKey.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                pupKey.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-pup");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("pup");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("pup");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.pdnKey.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                pdnKey.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-pdn");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("pdn");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("pdn");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        //------------  SEVENTH ROW ----------------//
        this.upBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                upBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-up");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("up");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("up");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.leftBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                leftBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-left");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("left");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("lett");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.downBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                downBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-down");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("down");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("down");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.rightBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                rightBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-right");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("right");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("right");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
        this.insBtn.setOnTouchListener(new View.OnTouchListener() {
            private Handler mHandler;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                insBtn.startAnimation(blinkAnimation);
                if (event.getAction() == MotionEvent.ACTION_UP){
                    if (mHandler == null) return true;
                    mHandler.removeCallbacks(mAction);
                    mHandler = null;
                    sd.send("U-ins");
                }
                else if (event.getAction() == MotionEvent.ACTION_DOWN){
                    if (mHandler != null) return true;
                    mHandler = new Handler();
                    mHandler.postDelayed(mAction, 170);
                    sd.send("ins");
                }
                return false;
            }
            final Runnable mAction = new Runnable() {
                @Override public void run() {
                    sd.send("ins");
                    mHandler.postDelayed(this, 50);
                }
            };
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int menu_id=item.getItemId();
        if(menu_id==R.id.navmouse)
        {
            Intent i1 = new Intent(MainActivity2.this,EntryActivity.class);
            startActivity(i1);
        }
        if(menu_id==R.id.pptMode)
        {
            Intent i1 = new Intent(MainActivity2.this,presentationActivity.class);
            startActivity(i1);
        }
        if(menu_id==R.id.logout)
        {
            Intent i1 = new Intent(MainActivity2.this,MainActivity.class);
            sd.exit();
            startActivity(i1);

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i1 = new Intent(MainActivity2.this,MainActivity.class);
        sd.exit();
        startActivity(i1);
    }
}
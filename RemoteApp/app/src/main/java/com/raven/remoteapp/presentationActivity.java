package com.raven.remoteapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.raven.Sender.Sender;

public class presentationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public boolean isDown=false;
    public boolean flag=false;
    public View pad,padBackground;
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public FrameLayout frameLayout;
    public int height,width;
    public Sender sd;
    public float x,y;
    public long lastClickTime=0;
    public Button leftPptBtn,rightPptBtn,slideShowBtn;
    public ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentation);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
        pad=findViewById(R.id.padView4);
        leftPptBtn=findViewById(R.id.leftPptBtn);
        padBackground=findViewById(R.id.padView3);
        slideShowBtn=findViewById(R.id.slideShowBtn);
        rightPptBtn=findViewById(R.id.rightPptBtn);

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //-----------------------------------
        height=pad.getMeasuredHeight();
        width=pad.getMeasuredWidth();
        sd=Sender.getInstance();

        sd.send(height+"@"+width);

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
                if(x>0 && x<width && y>0 && y<height) sd.send(x+","+y);
            }
            if(event.getActionMasked()==MotionEvent.ACTION_POINTER_DOWN && pointerIndex==1) {
                sd.send("rcd");
                sd.send("rcu");
                isDown=true;
            }
            return true;
        });

        leftPptBtn.setOnTouchListener((view, event) -> {
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-left");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("left");
            return true;
        });
        rightPptBtn.setOnTouchListener((view, event) -> {
            if(event.getAction()== MotionEvent.ACTION_UP) sd.send("U-right");
            else if(event.getAction()== MotionEvent.ACTION_DOWN) sd.send("right");
            return true;
        });
        slideShowBtn.setOnClickListener(view -> {
            if(!flag)
            {
                slideShowBtn.setForeground(getDrawable(R.drawable.slideshowonbtn));
                flag=true;
                sd.send("f5");
                sd.send("U-f5");
            }
            else if(flag)
            {
                slideShowBtn.setForeground(getDrawable(R.drawable.slideshowoffbtn));
                flag=false;
                sd.send("esc");
                sd.send("U-esc");
            }

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

        if(menu_id==R.id.navkeyboard)
        {
            Intent i1 = new Intent(presentationActivity.this,MainActivity2.class);
            startActivity(i1);
        }
        if(menu_id==R.id.navmouse)
        {
            Intent i1 = new Intent(presentationActivity.this,EntryActivity.class);
            startActivity(i1);
        }

        if(menu_id==R.id.logout)
        {
            Intent i1 = new Intent(presentationActivity.this,MainActivity.class);
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
        Intent i1 = new Intent(presentationActivity.this,MainActivity.class);
        startActivity(i1);
    }
}
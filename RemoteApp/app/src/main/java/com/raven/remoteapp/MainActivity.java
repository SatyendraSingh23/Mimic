package com.raven.remoteapp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    public GifImageView gif;
    public boolean isWifiEnabled = false;
    public ImageView iv, logoIv;
    public ImageView wifiBtn;
    public Animation animation;
    public TextView pdbyTv, ravenTv;
    public Button hotspotButton;
    public boolean isHotspotEnabled = false, isInternetConnected = false;
    public WifiApManager wifiApManager;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        animateRaven();
        wifiBtn = findViewById(R.id.wifiBtn);
        hotspotButton = findViewById(R.id.hotspotBtn);
        this.animation = AnimationUtils.loadAnimation(this, R.anim.blink_anim);
        this.wifiBtn.startAnimation(animation);

        // ------------ CODE FOR PERMISSIONS START--------------//
        Dexter.withContext(getApplicationContext()).
                withPermissions(Manifest.permission.INTERNET,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_NETWORK_STATE,
                        Manifest.permission.CHANGE_WIFI_STATE,
                        Manifest.permission.WRITE_SETTINGS,
                        Manifest.permission.CHANGE_NETWORK_STATE
                ).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                setListeners();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
        //------------ CODE FOR PERMISSION ENDS----------//


        //-----------CODE FOR NETWORK CONNECTIVITY STATUS STARTS------------------//
        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .build();

        ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback() {

            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                if(!isHotspotEnabled) return;
                runOnUiThread(() -> hotspotButton.setForeground(AppCompatResources.getDrawable(MainActivity.this, R.drawable.gobutton)));
                isInternetConnected = true;
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                isInternetConnected = false;
                runOnUiThread(() -> hotspotButton.setForeground(AppCompatResources.getDrawable(MainActivity.this, R.drawable.hotspotbtn)));
            }

            @Override
            public void onUnavailable() {
                super.onUnavailable();
                runOnUiThread(() -> hotspotButton.setForeground(AppCompatResources.getDrawable(MainActivity.this, R.drawable.hotspotbtn)));
                isInternetConnected = false;
            }


            @Override
            public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
                super.onCapabilitiesChanged(network, networkCapabilities);
            }
        };
        ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);
        connectivityManager.requestNetwork(networkRequest, networkCallback);
        //----------CODE FOR NETWORK CONNECTIVITY ENDS ---------//

        wifiApManager=new WifiApManager(MainActivity.this);
        Thread t=new Thread(() -> {
            try {
                while(true) {
                    isHotspotEnabled=wifiApManager.isWifiApEnabled();
                    Thread.sleep(500);
                }
            }catch (Exception e)
            {

            }
        });
        t.start();
    }

    private void animateRaven() {
        logoIv = findViewById(R.id.ravenLogo);
        pdbyTv = findViewById(R.id.pdby);
        ravenTv = findViewById(R.id.raven);
        //-----------------------------------------------------------------
        Animation zoomOut = AnimationUtils.loadAnimation(this, R.anim.zoomout);
        zoomOut.setDuration(1000);
        Animation lefttoright = AnimationUtils.loadAnimation(this, R.anim.lefttoright);
        lefttoright.setDuration(2000);
        logoIv.startAnimation(zoomOut);
        pdbyTv.startAnimation(lefttoright);
        ravenTv.startAnimation(AnimationUtils.loadAnimation(this, R.anim.lefttoright));

        //-----------------------------------------------------------------


        gif = findViewById(R.id.gifImageView);
        iv = findViewById(R.id.imageView2);
        iv.setVisibility(View.INVISIBLE);
        iv.animate().alpha(1).setDuration(3900).withEndAction(() -> iv.setVisibility(View.VISIBLE));
        iv.animate();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void setListeners() {
        this.wifiBtn.setOnClickListener(view -> {
            Animation anim = AnimationUtils.loadAnimation(this, R.anim.blink_anim);
            anim.setDuration(100);
            this.wifiBtn.startAnimation(anim);
            if(isHotspotEnabled && isInternetConnected)
            {
                Toast.makeText(MainActivity.this,"Turn off hotspot and internet both", Toast.LENGTH_SHORT).show();
                isHotspotEnabled=true;
                return;
            }
            if (isWifiEnabled) {
                isHotspotEnabled=false;
                Intent intent2 = new Intent(MainActivity.this, ChoiceActivity.class);
                startActivity(intent2);
            } else {
                Intent intent;
                intent = new Intent(Settings.Panel.ACTION_INTERNET_CONNECTIVITY);
                startActivity(intent);
            }

        });

        hotspotButton.setOnClickListener(view -> {
            System.out.println("-----------------"+wifiApManager.isWifiApEnabled()+"------"+wifiApManager.getWifiApState());
            if(isWifiEnabled){
                Toast.makeText(MainActivity.this,"Turn off Wifi", Toast.LENGTH_SHORT).show();
                isHotspotEnabled=false;
                return;
            }
            if(isHotspotEnabled && isInternetConnected) {
                Intent i=new Intent(MainActivity.this,ChoiceActivity.class);
                startActivity(i);
                return;
            }
            Intent intent=new Intent(Intent.ACTION_MAIN,null);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                final ComponentName componentName;
                componentName=new ComponentName("com.android.settings","com.android.settings.TetherSettings");
                intent.setComponent(componentName);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(wifiStateReceiver, intentFilter);
    }

    private final BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);
            if (wifiStateExtra == WifiManager.WIFI_STATE_ENABLED) {
                MainActivity.this.isWifiEnabled = true;
                hotspotButton.setForeground(AppCompatResources.getDrawable(MainActivity.this,R.drawable.hotspotbtn));
                wifiBtn.setForeground(AppCompatResources.getDrawable(MainActivity.this,R.drawable.gobutton));
            }
            if (wifiStateExtra == WifiManager.WIFI_STATE_DISABLED) {

                MainActivity.this.isWifiEnabled = false;
                wifiBtn.setForeground(AppCompatResources.getDrawable(MainActivity.this,R.drawable.wifibutton));
            }
        }
    };

}
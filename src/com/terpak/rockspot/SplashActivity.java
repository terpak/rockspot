package com.terpak.rockspot;

import com.terpak.rockspot.wireless.WifiDirectBroadcastReceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.os.Bundle;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        Assets.masterManager = (WifiP2pManager)getSystemService(Context.WIFI_P2P_SERVICE);
        Assets.masterChannel = Assets.masterManager.initialize(this, getMainLooper(), null);
        Assets.masterReceiver = new WifiDirectBroadcastReceiver(Assets.masterManager, Assets.masterChannel, this.getApplicationContext());
        
        Assets.masterFilter = new IntentFilter();
        Assets.masterFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        Assets.masterFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        Assets.masterFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        Assets.masterFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
        
        Intent intent = new Intent(this, ScanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    
    @Override
    protected void onResume(){
    	super.onResume();
    	registerReceiver(Assets.masterReceiver, Assets.masterFilter);
    }
    
    @Override
    protected void onPause(){
    	super.onPause();
    	unregisterReceiver(Assets.masterReceiver);
    }
}

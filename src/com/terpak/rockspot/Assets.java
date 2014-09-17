package com.terpak.rockspot;

import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;

import com.terpak.rockspot.wireless.WifiDirectBroadcastReceiver;

public class Assets {
	public static WifiDirectBroadcastReceiver masterReceiver;
	public static IntentFilter masterFilter;
	public static WifiP2pManager masterManager;
	public static Channel masterChannel;
}

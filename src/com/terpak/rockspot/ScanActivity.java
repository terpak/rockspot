package com.terpak.rockspot;

import java.util.ArrayList;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*TODO
 * -Deal with possible duplicate server names
 * -Store the WifiP2pConfig for the servers
 * -Make the "no servers found" and "error finding servers" rows unclickable
 */

public class ScanActivity extends Activity {
	private ListView serverList;
	private ArrayAdapter<String> serverListAdapter;
	private ArrayList<String> serverNameList;
	
	private BroadcastReceiver internalReceiver = new BroadcastReceiver(){
		@Override
		public void onReceive(Context context, Intent intent) {
			
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        
        //Set the activity to listen for PEER updates from the master bcast receiver
		LocalBroadcastManager.getInstance(this).registerReceiver(internalReceiver, new IntentFilter("PEERS"));
        
        serverNameList = new ArrayList<String>();
        serverList = (ListView)findViewById(R.id.serverList);
        serverListAdapter = new ArrayAdapter<String>(this, R.layout.server_list_item, serverNameList);
        serverList.setAdapter(serverListAdapter);
        
        Assets.masterManager.discoverPeers(Assets.masterChannel, new WifiP2pManager.ActionListener() {
			@Override
			public void onSuccess() {
				//Do nothing
			}
			
			@Override
			public void onFailure(int reason) {
				serverNameList.clear();
				serverNameList.add("Error searching for servers.");
			}
		});
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		LocalBroadcastManager.getInstance(this).registerReceiver(internalReceiver, new IntentFilter("PEERS"));
	}
	
	@Override
	protected void onResume(){
		super.onResume();
		LocalBroadcastManager.getInstance(this).unregisterReceiver(internalReceiver);
	}
}

package com.terpak.rockspot.wireless;

import android.net.wifi.p2p.WifiP2pConfig;

public class Server {
    private String name = "";
    private WifiP2pConfig config = new WifiP2pConfig();
     
    /*********** Set Methods ******************/
     
    public void setName(String name)
    {
        this.name = name;
    }
     
    public void setConfig(WifiP2pConfig config)
    {
        this.config = config;
    }
     
    /*********** Get Methods ****************/
     
    public String getName()
    {
        return this.name;
    }
     
    public WifiP2pConfig getConfig()
    {
        return this.config;
    }
}

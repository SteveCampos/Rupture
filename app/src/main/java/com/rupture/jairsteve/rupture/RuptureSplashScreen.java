package com.rupture.jairsteve.rupture;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.net.wifi.*;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.security.AccessController;
import java.util.List;

import Controller.red_wlan_metods;
import Model.red_wlan;


public class RuptureSplashScreen extends Activity {


    public static final String fontPathMonserratBold = "fonts/Montserrat-Bold.ttf";

    private TextView textView_Splash_Screen_App_Name;
    private TextView textView_Splash_Screen_Developer_Name;

    WifiManager wifiManager;
    red_wlan wlan;
    wlan_metods wlanMetodsDB;
    vendor_metods vendorMetodsDB;
    red_wlan_metods wlanMetods;

    ScanDBHelper scanDBHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rupture_splash_screen);

        /*
        Typeface typefaceMonserratBold = Typeface.createFromAsset(getAssets(), fontPathMonserratBold);
        //CAMBIAR ESTILO DE FUENTE A MONTSERRAT( se ve más cool)
        textView_Splash_Screen_App_Name = (TextView) findViewById(R.id.textView_Splash_Screen_App_Name);
        textView_Splash_Screen_App_Name.setTypeface(typefaceMonserratBold);
        textView_Splash_Screen_Developer_Name = (TextView) findViewById(R.id.textView_Splash_Screen_Developer_Name);
        textView_Splash_Screen_Developer_Name.setTypeface(typefaceMonserratBold);
*/
        Log.d("UI","TIPO DE LETRA CAMBIADA...");

        scanDBHelper = new ScanDBHelper(this);
        //RECORDAR MANDAR EL MISMO CONTEXTO A AMBAS INSTANCIAS
        Log.d("IMPORTANT","AQUÍ CREO YA SE DEBIÓ CREAR LA BASE DE DATOS Y SUS MENSAJES");


        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);

        if(wifiManager.getWifiState() == wifiManager.WIFI_STATE_ENABLED){


            wlanMetodsDB = new wlan_metods(this);
            vendorMetodsDB = new vendor_metods(this);
            wlanMetods = new red_wlan_metods();

            //Register Wifi Scan Results Receiver
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);

            registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {

                    List<ScanResult> scanResultsList = wifiManager.getScanResults();
                    final int sizeListScan = scanResultsList.size();
                    Log.d("Wifi Scan count ...", "" + sizeListScan);

                    for(int i=0; i<sizeListScan; i++){
                        wlan = new red_wlan();
                        //Log.d("SSID",scanResultsList.get(i).SSID.toString());
                        wlan.setSsid(scanResultsList.get(i).SSID.toString());
                        //Log.d("BSSID", scanResultsList.get(i).BSSID.toString());
                        wlan.setBssid(scanResultsList.get(i).BSSID.toString());
                        //Log.d("CAPABILITIES", scanResultsList.get(i).capabilities.toString());
                        wlan.setCapabilities(scanResultsList.get(i).capabilities.toString());
                        //Log.d("FREQUENCY", "" + scanResultsList.get(i).frequency);
                        wlan.setFrequency(scanResultsList.get(i).frequency);
                        //Log.d("LEVEL", "" + scanResultsList.get(i).level);
                        wlan.setLevel(scanResultsList.get(i).level);
                        //Log.d("TIMESTAMP", ""+scanResultsList.get(i).timestamp);
                        wlan.setTimestamp((int) scanResultsList.get(i).timestamp);

                        //INSERTO CADA REGISTRO EN LA TABLA WLAN (:
                        wlanMetodsDB.insertWlan(wlan);

                    }
                }
            }, intentFilter);
        }

        //start Wifi Scan
        wifiManager.startScan();

        Intent intent = new Intent(this, mostrarRedes.class);

        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.rupture_splash_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

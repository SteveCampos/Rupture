package com.rupture.jairsteve.rupture;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import Model.Constants;


public class mostrarRedes extends Activity {

    TextView textView;
    wlan_metods wlanMetodsDB;
    vendor_metods vendorMetodDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_redes);

        wlanMetodsDB = new wlan_metods(getApplicationContext());
        vendorMetodDB = new vendor_metods(getApplicationContext());
        textView = (TextView)findViewById(R.id.textViewMostrarResultados);



        try{
            Cursor cursor = wlanMetodsDB.readWlan();
            if (cursor.moveToFirst()) {
                do {
                    textView.append(", SSID :  " + cursor.getColumnIndexOrThrow(Constants.red_wlan._COLUMN_NAME_BSSID));

                    textView.append(", BSSID : " + cursor.getColumnIndexOrThrow(Constants.red_wlan._COLUMN_NAME_SSID));

                    textView.append(", CAPAS : "+cursor.getColumnIndexOrThrow(Constants.red_wlan._COLUMN_NAME_CAPABILITIES));
                    textView.append(", ID_VENDOR : " + cursor.getColumnIndexOrThrow(Constants.red_wlan._COLUMN_NAME_ID_VENDOR));
                    textView.append(", LEVEL : " + cursor.getColumnIndexOrThrow(Constants.red_wlan._COLUMN_NAME_LEVEL));

                    textView.append(", VENDOR : "+vendorMetodDB.getVendorForID(String.valueOf(cursor.getColumnIndexOrThrow(Constants.red_wlan._COLUMN_NAME_ID_VENDOR))));
                    textView.append(", PASSWORD : " + cursor.getColumnIndexOrThrow(Constants.red_wlan._COLUMN_NAME_PASSWORD));
                    textView.append("--------------------------------------------------------------------");


                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){
            Log.d("Exception",""+e);

        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mostrar_redes, menu);
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

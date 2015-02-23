package com.rupture.jairsteve.rupture;

/**
 * Created by JairSteve on 01/11/2014.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import Controller.red_wlan_metods;
import Model.Constants;
import Model.red_wlan;

public class wlan_metods {
    ScanDBHelper scanDBHelper;
    Cursor cursor;
    red_wlan_metods metods;
    vendor_metods vendor;

    {
        metods = new red_wlan_metods();
    }

        public wlan_metods(Context context) {
        scanDBHelper  = new ScanDBHelper(context);
    }

    public void insertWlan(red_wlan wlan){
        //OBTENGO LA BASE DE DATOS EN MODO ESCRIBIBLE
        Log.d("DB", "OBTENIENDO DB EN MODO ESCRIBIBLE...");
        SQLiteDatabase db = scanDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Constants.red_wlan._COLUMN_NAME_ID, metods.splitBssid(wlan.getBssid())); // ID (MAC SPLIT)
        values.put(Constants.red_wlan._COLUMN_NAME_BSSID, wlan.getBssid()); // BSSID (MAC)
        values.put(Constants.red_wlan._COLUMN_NAME_SSID, wlan.getSsid()); // SSID (WLAN NAME)
        values.put(Constants.red_wlan._COLUMN_NAME_CAPABILITIES, wlan.getCapabilities()); // CAPABILITIES (PROTECTION)
        values.put(Constants.red_wlan._COLUMN_NAME_FREQUENCY, wlan.getFrequency()); // FREQUENCY (2.4Ghz)
        values.put(Constants.red_wlan._COLUMN_NAME_LEVEL, wlan.getLevel()); // LEVEL (SIGNAL 0 -100)
        values.put(Constants.red_wlan._COLUMN_NAME_TIMESTAMP, wlan.getTimestamp()); // EL TIEMPO EN QUE SE MANDAN LOS PAQUETES CREOO XD
        values.put(Constants.red_wlan._COLUMN_NAME_ID_VENDOR, metods.obtain_id_vendor(wlan.getBssid())); // ID_VENDOR (PRIMEROS 6 DÍGITOS DE LA MAC)
        String id_vendor = metods.obtain_id_vendor(wlan.getBssid());
        String name_vendor = vendor.getVendorForID(id_vendor);
        String twoMiddleDigits = metods.obtainTwoMiddleDitigs(metods.splitBssid(wlan.getBssid()));
        String fourLastDigits = metods.obtain_ssid_last_four_digits(wlan.getSsid());

        if(name_vendor!=null) {
            String firstLetter = ""+name_vendor.charAt(0);
            String password = firstLetter + id_vendor + twoMiddleDigits + fourLastDigits;
            values.put(Constants.red_wlan._COLUMN_NAME_PASSWORD, password ); // VENDOR FIRST LETTER + (ID_VENDOR)MAC REAL 6 DIGITS + 2 DIGITS NEXT + 4 DIGITS TO WLAN
        }
        // Inserting Row
        Log.d("INSERTANDO","insertando registro...");
        db.insert(Constants.red_wlan._TABLE_NAME, null, values);
        Log.d("INSERTANDO","REGISTRO INSERTADO..");
        db.close(); // Closing database connection
        Log.d("DB","base de datos cerrada despupes de insertar registro");
    }

    public Cursor readWlan(){

        Log.d("DB", "OBTENIENDO DB EN MODO LECTURA READWLAN...");
        SQLiteDatabase db = scanDBHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                Constants.red_wlan._COLUMN_NAME_ID,
                Constants.red_wlan._COLUMN_NAME_BSSID,
                Constants.red_wlan._COLUMN_NAME_SSID,
                Constants.red_wlan._COLUMN_NAME_CAPABILITIES,
                Constants.red_wlan._COLUMN_NAME_FREQUENCY,
                Constants.red_wlan._COLUMN_NAME_LEVEL,
                Constants.red_wlan._COLUMN_NAME_TIMESTAMP,
                Constants.red_wlan._COLUMN_NAME_ID_VENDOR,
                Constants.red_wlan._COLUMN_NAME_PASSWORD
        };

        //OBTENIENDO LA CONSULTA

        cursor= db.query(
                Constants.red_wlan._TABLE_NAME,             // The table to query
                projection,                               // The columns to return
                null,                                     // The columns for the WHERE clause
                null,                                   // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                     // The sort order
        );

        db.close();

        return cursor;
    }
}

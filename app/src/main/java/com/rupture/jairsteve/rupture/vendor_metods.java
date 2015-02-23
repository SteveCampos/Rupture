package com.rupture.jairsteve.rupture;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import Model.Constants;

/**
 * Created by JairSteve on 02/11/2014.
 */
public class vendor_metods {
    ScanDBHelper scanDBHelper;
    String vendor = "";
    AssetManager assetManager = null;
    public vendor_metods(Context context) {
        scanDBHelper = new ScanDBHelper(context);
        assetManager = context.getAssets();
    }

    public String getVendorForID(String id_vendor){
        SQLiteDatabase db = scanDBHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                Constants.vendor._COLUMN_NAME_ID,
                Constants.vendor._COLUMN_NAME_NAME,
        };
        String[] valuesArgs = {
                id_vendor
        } ;

        Cursor c = db.query(
                Constants.vendor._TABLE_NAME,             // The table to query
                projection,                               // The columns to return
                Constants.vendor._COLUMN_NAME_ID,         // The columns for the WHERE clause
                valuesArgs,                                // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                     // The sort order
        );


        c.moveToFirst();
        vendor = c.getString(
                c.getColumnIndexOrThrow(Constants.vendor._COLUMN_NAME_NAME)
        );
        db.close();
        return vendor;
    }

    public void llenarTablaVendor(){

        SQLiteDatabase db = scanDBHelper.getWritableDatabase();

        InputStream archivo = null;
        FileReader fr = null;
        BufferedReader br = null;


        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            String path="table_vendor.txt";

            archivo = assetManager.open(path);

            InputStreamReader chapterReader = new InputStreamReader(archivo);
            br = new BufferedReader(chapterReader);


            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null) {
                ContentValues values = new ContentValues();

                String vendor = "";
                String delimitadores=" ";
                String[] columnsVendor = linea.split(delimitadores);
                values.put(Constants.vendor._COLUMN_NAME_ID, columnsVendor[0]); // ID VENDOR (MAC FIRST SIX DIGITS)
                Log.d("Agregando ID : ", columnsVendor[0] + "\t");

                for(int j=1;j<columnsVendor.length;j++){
                    vendor+=columnsVendor[j]+" ";
                }
                values.put(Constants.vendor._COLUMN_NAME_NAME, vendor); // VENDOR NAME
                Log.d("VENDOR : ", vendor+"\n");

                // Inserting Row
                db.insert(Constants.vendor._TABLE_NAME, null, values);
                 // Closing database connection
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            db.close();
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }

    }

}

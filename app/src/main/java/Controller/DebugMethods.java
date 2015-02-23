package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.*;

import Model.Constants;

/**
 * Created by JairSteve on 01/11/2014.
 */
public class DebugMethods {

    public static void main (String args[]){
        Context context = null;
        String ssid_prueba = "WLAN_6DA4";
        String mac_prueba = "64:70:02:8C:6D:A4";

        red_wlan_metods metods = new red_wlan_metods();

        System.out.println("ID_VENDOR : "+metods.obtain_id_vendor(mac_prueba));
        System.out.println("LAST FOUR DIGITS SSID : "+metods.obtain_ssid_last_four_digits(ssid_prueba));

        System.out.println("SPLIT MAC : "+metods.splitBssid(mac_prueba));

        System.out.println("TIPO WLAN : " + metods.obtainTipoWlan(ssid_prueba));
        System.out.println("TWO MIDDLE DIGITS : " + metods.obtainTwoMiddleDitigs(metods.splitBssid(mac_prueba)));


        InputStream archivo = null;
        BufferedReader br = null;


        try {
            // Apertura del fiche|ro y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            String path="C:\\Users\\JairSteve\\Desktop\\AppRupture\\Icons\\table_vendor.txt";

            File file = new File(path);
            br = new BufferedReader(new FileReader(file));
            // Lectura del fichero
            String linea;
            while((linea=br.readLine())!=null) {
//                ContentValues values = new ContentValues();

                String vendor = "";
                String delimitadores=" ";
                String[] columnsVendor = linea.split(delimitadores);
                //values.put(Constants.vendor._COLUMN_NAME_ID, columnsVendor[0]); // ID VENDOR (MAC FIRST SIX DIGITS)
  //              Log.d("Agregando ID : ", columnsVendor[0] + "\t");

                for(int j=1;j<columnsVendor.length;j++){
                    vendor+=columnsVendor[j]+" ";
                }
  //              values.put(Constants.vendor._COLUMN_NAME_NAME, vendor); // VENDOR NAME
                System.out.println("INSERT INTO vendor(id_vendor, vendor_name) VALUES ( \""+columnsVendor[0]+"\",  \""+vendor+"\" );");
//                Log.d("VENDOR : ", vendor+"\n");

                // Inserting Row
                //db.insert(Constants.vendor._TABLE_NAME, null, values);
                // Closing database connection
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            //db.close();

        }


    }
}

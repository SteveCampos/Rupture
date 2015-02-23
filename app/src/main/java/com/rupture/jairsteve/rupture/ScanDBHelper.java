package com.rupture.jairsteve.rupture;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import Model.Constants;

/**
 * Created by JairSteve on 01/11/2014.
 */
public class ScanDBHelper extends SQLiteOpenHelper{

    vendor_metods vendor;
    Context mcontext;
    public ScanDBHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null,Constants.DATABASE_VERSION);
        //NO ESTOY SEGURO SI FUNCIONARÁ ESTA VARIABLE EN EL SIGUIENTE MÉTODO
        mcontext = context;


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("DB : ", "Creando la tabla wlan..");
        sqLiteDatabase.execSQL(Constants.SQL_CREATE_TABLE_WLAN);
        Log.d("DB : ", "TABLA WLAN CREADA");
        Log.d("DB : ", "Creando la tabla vendor..");
        sqLiteDatabase.execSQL(Constants.SQL_CREATE_TABLE_VENDOR);
        Log.d("DB : ", "TABLA VENDOR CREADA");
        //AGREGO REGISTROS A LA TABLA VENDOR
        Log.d("DB : ", "INSERTANDO REGISTROS A LA TABLA VENDOR");
        vendor = new vendor_metods(mcontext);
        vendor.llenarTablaVendor();
        Log.d("DB : ", "REGISTROS INSERTADOS A LA TABLA VENDOR");
        sqLiteDatabase.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL(Constants.SQL_DELETE_TABLE_WLAN);
        sqLiteDatabase.execSQL(Constants.SQL_DELETE_TABLE_VENDOR);
        onCreate(sqLiteDatabase);
    }

}

package Model;

import android.provider.BaseColumns;

/**
 * Created by JairSteve on 01/11/2014.
 */
public class Constants {


    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "scan.db";

    public Constants() {
    }


    public static abstract class red_wlan implements BaseColumns{

        public static final String _TABLE_NAME = "red_wlan";
        public static final String _COLUMN_NAME_ID="mac_adress";
        public static final String _COLUMN_NAME_BSSID="bssid";
        public static final String _COLUMN_NAME_SSID="ssid";
        public static final String _COLUMN_NAME_CAPABILITIES="capabilities";
        public static final String _COLUMN_NAME_FREQUENCY="frequency";
        public static final String _COLUMN_NAME_LEVEL="level";
        public static final String _COLUMN_NAME_TIMESTAMP="timestamp";

        public static final String _COLUMN_NAME_ID_VENDOR="id_vendor";
        public static final String _COLUMN_NAME_PASSWORD="password";
        public static final String _COLUMN_NAME_TIPO_WLAN="tipo_wlan";


    }

    public static  abstract class vendor implements BaseColumns{
        public static final String _TABLE_NAME = "vendor";
        public static final String _COLUMN_NAME_ID="id_vendor";
        public static final String _COLUMN_NAME_NAME="name";

    }

    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE =" INTEGER";
    public static final String COMMA_SEP = ",";
    public static final String PRIMARY_KEY =" PRIMARY KEY";
    public static final String SQL_CREATE_TABLE_WLAN =
            "CREATE TABLE " + red_wlan._TABLE_NAME + " (" +

                    red_wlan._ID + TEXT_TYPE + PRIMARY_KEY + COMMA_SEP +

                    red_wlan._COLUMN_NAME_BSSID + TEXT_TYPE + COMMA_SEP +
                    red_wlan._COLUMN_NAME_SSID + TEXT_TYPE + COMMA_SEP +
                    red_wlan._COLUMN_NAME_CAPABILITIES + TEXT_TYPE + COMMA_SEP +
                    red_wlan._COLUMN_NAME_FREQUENCY + INTEGER_TYPE + COMMA_SEP +
                    red_wlan._COLUMN_NAME_LEVEL + INTEGER_TYPE + COMMA_SEP +
                    red_wlan._COLUMN_NAME_TIMESTAMP + INTEGER_TYPE + COMMA_SEP +

                    red_wlan._COLUMN_NAME_ID_VENDOR + INTEGER_TYPE + COMMA_SEP +
                    red_wlan._COLUMN_NAME_PASSWORD + TEXT_TYPE + COMMA_SEP +
                    red_wlan._COLUMN_NAME_TIPO_WLAN + INTEGER_TYPE +

                    " )";

    public static final String SQL_CREATE_TABLE_VENDOR =
            "CREATE TABLE " + vendor._TABLE_NAME + " (" +
                    vendor._COLUMN_NAME_ID + TEXT_TYPE + PRIMARY_KEY + COMMA_SEP +
                    vendor._COLUMN_NAME_NAME + TEXT_TYPE +
                    " )";



    public static final String SQL_DELETE_TABLE_WLAN =
            "DROP TABLE IF EXISTS " + red_wlan._TABLE_NAME;
    public static final String SQL_DELETE_TABLE_VENDOR =
            "DROP TABLE IF EXISTS " + vendor._TABLE_NAME;

}

package Controller;

import Model.red_wlan;

/**
 * Created by JairSteve on 01/11/2014.
 */
public class red_wlan_metods {


    public static final String _SSID = "WLAN_XXXX";
    String bssid_prueba = "64:70:02:8C:6D:A4";

    red_wlan red;
    String splitMac = "";
    String id_vendor  = "";
    String ssid_last_four_digits = "";
    String twoMiddleDigits="";
    int tipoWlan=0;

    //MÉTODOS PARA OBTENER INFORMACIÓN ADICIONAL NECESARIA

    public String splitBssid(String bssid){
        splitMac="";
        String delimitadores=":";
        String[] columnsVendor = bssid.split(delimitadores);
        for(int i=0;i<columnsVendor.length;i++){
            splitMac+=columnsVendor[i];
        }
        return splitMac;
    }

    public String obtain_id_vendor(String mac){
        //PUEDE OCURRIR QUE LA MAC TENGA MÁS DIGITOS DE LO ESPERADO
        //

        id_vendor="";
        for (int i = 0; i <8 ; i++) {
            if(i==2||i==5){
                // NO AGREGO LOS 2 PUNTOS
            }else{
                id_vendor+=""+mac.charAt(i);
            }
        }
        return id_vendor;
    }

    public String obtain_ssid_last_four_digits(String ssid){


        //PRIMERO DEBEMOS VERIFICAR SI ES WLAN_XXXX

        ssid_last_four_digits = "";
        if(ssid.length()==_SSID.length()){

            for (int i = 5; i <ssid.length() ; i++) {
                ssid_last_four_digits+=ssid.charAt(i);
            }

        }else{
            ssid_last_four_digits="XXXX";
        }

        return ssid_last_four_digits;
    }

    public String obtainTwoMiddleDitigs(String splitMac){
        twoMiddleDigits="";
        for (int i = 0; i <splitMac.length() ; i++) {
            if (i==6||i==7){
                twoMiddleDigits+=splitMac.charAt(i);
            }
        }
        return twoMiddleDigits;
    }

    public String concatPassword(String first_letter_vendor, String first_six_digits, String middle_two_digits, String last_four_digits){
        return first_letter_vendor + first_six_digits + middle_two_digits + last_four_digits;
    }

    public int obtainTipoWlan(String ssid){
        String model = "WLAN";
        String ssidFourDigits = "";
        for (int i = 0; i < 4 ; i++) {
            ssidFourDigits+=""+ssid.charAt(i);
        }
        if (ssidFourDigits.equals(model)){
            tipoWlan=1;
        }else{
            tipoWlan=0;
        }

        return tipoWlan;
    }


}

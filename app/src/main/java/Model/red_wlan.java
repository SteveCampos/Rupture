package Model;

/**
 * Created by JairSteve on 01/11/2014.
 */
public class red_wlan {
    private String bssid;
    private String ssid;
    private String capabilities;
    private int frequency;
    private int level;
    private int timestamp;


    //ADICIONALES PARA AGREGAR A LA BASE DE DATOS

    private int tipo_wlan;
    private String id_vendor;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public String getId_vendor() {
        return id_vendor;
    }

    public void setId_vendor(String id_vendor) {
        this.id_vendor = id_vendor;
    }

    public int getTipo_wlan() {
        return tipo_wlan;
    }

    public void setTipo_wlan(int tipo_wlan) {
        this.tipo_wlan = tipo_wlan;
    }
}

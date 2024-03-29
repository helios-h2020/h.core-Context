package eu.h2020.helios_social.core.context.ext;

import android.util.Log;

import eu.h2020.helios_social.core.context.Context;
import eu.h2020.helios_social.core.sensor.SensorValueListener;

/**
 * WifiContext - a WiFi-based context class<br/>
 * This class is used to detect whether a wifi given ssid is currently connected.
 * This class extends the base class Context.
 * Value updates are obtained from WifiSensor via SensorValueListener
 */
public class WifiContext extends Context implements SensorValueListener {

    private String ssid;
    private static final String TAG = "HeliosWifiContext";

    /**
     * Creates a ActivityContext
     * @param name the name of the context
     * @param ssid the Wifi network service set id (SSID).
     */
    public WifiContext(String name, String ssid) {
        this(null, name, ssid);
    }

    /**
     * Creates a ActivityContext
     * @param id the identifier of the context
     * @param name the name of the context
     * @param ssid the Wifi network service set id (SSID).
     */
    public WifiContext(String id, String name, String ssid) {
        super(id, name, false);
        this.ssid = ssid;
    }

    /**
     * Sets the service set id (SSID)
     * @param ssid the SSID
     */
    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    /**
     * Gets the service set id (SSID)
     * @return the SSID
     */
    public String getSsid() {
        return ssid;
    }

    /**
     * Receive the current wifi's ssid from the WifiSensor
     * {@link eu.h2020.helios_social.core.sensor.ext.WifiSensor}.
     * In order to receive the updates, this context should be registered as a SensorValueListener
     * for the sensor.
     * @param value the received value
     */
    @Override
    public void receiveValue(Object value) {
        Log.d(TAG, "received Wifi ssid value");
        String received_ssid = (String) value;
        setActive(received_ssid != null && received_ssid.equals(ssid));
    }
}

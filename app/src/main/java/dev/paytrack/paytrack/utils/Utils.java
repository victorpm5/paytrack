package dev.paytrack.paytrack.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by albert on 18/03/17.
 *
 * @author albert
 */
public class Utils {

    public static LatLng getLocationFromAddress(String address, Context context) {
        Geocoder coder = new Geocoder(context);
        List<Address> results = null;
        LatLng coordinates = null;
        try {
            try {
                results = coder.getFromLocationName(address, 5);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (results == null) {
                return null;
            }
            Address location = results.get(0);
            coordinates = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coordinates;
    }

}

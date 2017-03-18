package dev.paytrack.paytrack.foursquare;

import java.util.List;

/**
 * Created by albert on 18/03/17.
 *
 * @author albert
 */
public interface FoursquareAPI {

    List<FoursquareVenue> getVenuesFromCity(double latitude, double longitude);

}

package dev.paytrack.paytrack.utils;

import android.app.Activity;

/**
 * Created by albert on 18/03/17.
 * @author albert
 */
public class Preferences {

    private static OAuthUtils oAuthUtils;

    public static String getAuthToken(Activity activity) {
        if (oAuthUtils == null) {
            oAuthUtils = new OAuthUtils();
        }
        return oAuthUtils.getAuthToken(activity);
    }

}

package dev.paytrack.paytrack.utils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by albert on 18/03/17.
 * @author albert
 */
public class OAuthUtils {

    private static final String AUTH_SCOPE = "Manage your transactions";
    private static final String CLIENT_ID = "70b4ee41-2475-491c-bb15-33881819c5f4";
    private static final String CLIENT_SECRET_KEY = "Sd-meBHnjlXfh3UcJhSdyNOrQX8L8CzDxTGwH0A5hA2pavhn2vtGA-vhnbjTJ3yz05gTTqebs6qUugbUcXK9JQ";
    private static final String REDIRECT_URI = "http://localhost:8001";

    private String authToken;
    private Activity activity;

    public String getAuthToken(Activity activity) {
        this.activity = activity;
        if (this.authToken == null) {
            AccountManager accountManager = AccountManager.get(activity.getApplicationContext());
            this.authToken = String.valueOf(
                    accountManager.getAuthToken(new Account("PayTrack", "Implicit"),
                                                AUTH_SCOPE,
                                                new Bundle(),
                                                activity,
                                                new OnTokenAcquired(),
                                                new Handler()));
            openConnection();
        }
        return this.authToken;
    }

    private void openConnection() {

        try {
            URL url = new URL("https://simulator-api.db.com/gw/oidc/authorize?response_type=code&" +
                    "redirect_uri=" + REDIRECT_URI + "&client_id="+ CLIENT_ID);
            URLConnection conn = url.openConnection();
            conn.addRequestProperty("client_id", CLIENT_ID);
            conn.addRequestProperty("client_secret", CLIENT_SECRET_KEY);
            conn.setRequestProperty("Authorization", "OAuth " + authToken);
        } catch (IOException e) {
            Log.e("IOEx", "Exception while opening connection with OAuth2.0");
        }

    }

    private class OnTokenAcquired implements AccountManagerCallback<Bundle> {
        @Override
        public void run(AccountManagerFuture<Bundle> result) {
            // Get the result of the operation from the AccountManagerFuture.
            try {
                Bundle bundle = result.getResult();

                // The token is a named value in the bundle. The name of the value
                // is stored in the constant AccountManager.KEY_AUTHTOKEN.
                authToken = bundle.getString(AccountManager.KEY_AUTHTOKEN);

                Intent launch = (Intent) bundle.get(AccountManager.KEY_INTENT);
                if (launch != null) {
                    activity.startActivityForResult(launch, 0);
                }

            } catch (OperationCanceledException e) {
                Log.e(  "OperationCanceledEx",
                        "Exception while getting result in AccountManagerFuture class");
            } catch (IOException e) {
                Log.e(  "IOEx",
                        "Exception while getting result in AccountManagerFuture class");
            } catch (AuthenticatorException e) {
                Log.e(  "AuthenticatorEx",
                        "Exception while getting result in AccountManagerFuture class");
            }

        }
    }

}
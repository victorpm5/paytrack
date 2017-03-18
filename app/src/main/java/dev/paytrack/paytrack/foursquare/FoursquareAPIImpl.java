package dev.paytrack.paytrack.foursquare;


import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.InputStream;

/**
 * Created by albert on 18/03/17.
 *
 * @author albert
 */
public class FoursquareAPIImpl implements FoursquareAPI {

    private final String CLIENT_ID = "F40MR5W35BG15IRE43LZANESEHGE3LAGIV3I0TI3IEPJQNBP";
    private final String CLIENT_SECRET = "M0DYJPQVQZ0OVJCH4YAWVORDJVR5WWACAOYHXO1NQ0VZVARJ";

    private String latitude;
    private String longitute;

    private class FoursquareCall extends AsyncTask {

        String temp;

        @Override
        protected Object doInBackground(Object[] params) {
            temp = makeCall("https://api.foursquare.com/v2/venues/search?client_id=" + CLIENT_ID +
                    "&client_secret=" + CLIENT_SECRET + "&v=20130815&ll=" + latitude + "," + longitute);
            return "";
        }

        @Override
        protected void onPreExecute() {
            // we can start a progress bar here
        }

        @Override
        protected void onPostExecute(String result) {

        }

        public String makeCall(String url) {

            // string buffers the url
            StringBuffer buffer_string = new StringBuffer(url);
            String replyString = "";

            // instanciate an HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            // instanciate an HttpGet
            HttpGet httpget = new HttpGet(buffer_string.toString());

            try {
                // get the responce of the httpclient execution of the url
                HttpResponse response = httpclient.execute(httpget);
                InputStream is = response.getEntity().getContent();

                // buffer input stream the result
                BufferedInputStream bis = new BufferedInputStream(is);
                ByteArrayBuffer baf = new ByteArrayBuffer(20);
                int current = 0;
                while ((current = bis.read()) != -1) {
                    baf.append((byte) current);
                }
                // the result as a string is ready for parsing
                replyString = new String(baf.toByteArray());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // trim the whitespaces
            return replyString.trim();
        }
    }

}

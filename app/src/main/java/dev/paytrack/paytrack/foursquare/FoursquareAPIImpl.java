package dev.paytrack.paytrack.foursquare;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.ByteArrayBuffer;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.view.View;

/**
 * Created by albert on 18/03/17.
 * @author albert
 */
public class FoursquareAPIImpl implements FoursquareAPI {

    private final String CLIENT_ID = "F40MR5W35BG15IRE43LZANESEHGE3LAGIV3I0TI3IEPJQNBP";
    private final String CLIENT_SECRET = "M0DYJPQVQZ0OVJCH4YAWVORDJVR5WWACAOYHXO1NQ0VZVARJ";

    private String latitude;
    private String longitude;

    private List<FoursquareVenue> venueList = new ArrayList<>();

    @Override
    public List<FoursquareVenue> generateVenuesFromCity(double latitude, double longitude) {
        this.latitude = String.valueOf(latitude);
        this.longitude = String.valueOf(longitude);
        new FoursquareCall().execute();
        return venueList;
    }

    @Override
    public List<FoursquareVenue> getCurrentVenues() {
        return venueList;
    }

    private class FoursquareCall extends AsyncTask<View, Void, String> {

        String temp;

        @Override
        protected String doInBackground(View... urls) {
            // make Call to the url
            temp = makeCall("https://api.foursquare.com/v2/venues/search?client_id=" + CLIENT_ID +
                    "&client_secret=" + CLIENT_SECRET + "&v=20130815&ll=" + latitude + "," + longitude);
            return "";
        }

        @Override
        protected void onPreExecute() {
            // we can start a progress bar here
        }

        @Override
        protected void onPostExecute(String result) {
            if (temp == null) {
                // we have an error to the call
                // we can also stop the progress bar
            } else {
                // all things went right

                // parseFoursquare venues search result
                venueList = (ArrayList<FoursquareVenue>) parseFoursquare(temp);
            }
        }
    }

    public static String makeCall(String url) {

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

    private static ArrayList<FoursquareVenue> parseFoursquare(final String response) {

        ArrayList<FoursquareVenue> temp = new ArrayList<FoursquareVenue>();
        try {

            // make an jsonObject in order to parse the response
            JSONObject jsonObject = new JSONObject(response);

            // make an jsonObject in order to parse the response
            if (jsonObject.has("response")) {
                if (jsonObject.getJSONObject("response").has("venues")) {
                    JSONArray jsonArray = jsonObject.getJSONObject("response").getJSONArray("venues");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        FoursquareVenue poi = new FoursquareVenue();
                        if (jsonArray.getJSONObject(i).has("name")) {
                            poi.setName(jsonArray.getJSONObject(i).getString("name"));

                            if (jsonArray.getJSONObject(i).has("location")) {
                                if (jsonArray.getJSONObject(i).getJSONObject("location").has("address")) {
                                    if (jsonArray.getJSONObject(i).getJSONObject("location").has("city")) {
                                        poi.setCity(jsonArray.getJSONObject(i).getJSONObject("location").getString("city"));
                                    }
                                    if (jsonArray.getJSONObject(i).getJSONObject("location").has("lat")) {
                                        poi.setLatitude(jsonArray.getJSONObject(i).getJSONObject("location").getDouble("lat"));
                                    }
                                    if (jsonArray.getJSONObject(i).getJSONObject("location").has("lng")) {
                                        poi.setLongitute(jsonArray.getJSONObject(i).getJSONObject("location").getDouble("lng"));
                                    }
                                    if (jsonArray.getJSONObject(i).has("categories")) {
                                        if (jsonArray.getJSONObject(i).getJSONArray("categories").length() > 0) {
                                            if (jsonArray.getJSONObject(i).getJSONArray("categories").getJSONObject(0).has("icon")) {
                                                poi.setCategory(jsonArray.getJSONObject(i).getJSONArray("categories").getJSONObject(0).getString("name"));
                                            }
                                        }
                                    }
                                    temp.add(poi);
                                }
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<FoursquareVenue>();
        }
        return temp;

    }
}
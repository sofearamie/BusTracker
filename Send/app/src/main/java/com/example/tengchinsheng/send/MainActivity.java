package com.example.tengchinsheng.send;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {
    ///////location/////////////
    private LocationManager locationManager = null;
    private LocationListener locationListener = null;
    private Button btnGetLocation = null;
    private EditText editLocation = null;
     TextView tv;
   TextView tv1;
    // EditText printResult;
    private ProgressBar pb = null;
    private static final String TAG = "Debug";
    private Boolean flag = false;
    ////////////location////////////

    public String returnString, temp;
    public double stop_long;

    //static InputStream in;
    double latitude =3.45;
    double longi=102.123;
    String encoderString = "?lat=" + latitude + "&longi=" + longi ;
    String url = "http://203.223.144.218/staging/mmu/index.php" + URLEncoder.encode(encoderString);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //if you want to lock screen for always Portrait mode
        setRequestedOrientation(ActivityInfo
                .SCREEN_ORIENTATION_PORTRAIT);

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);
        System.out.println(url);

/////////////
        tv = (TextView) findViewById(R.id.textView);
        tv1 = (TextView) findViewById(R.id.textView1);
        //     printResult = (EditText) findViewById(R.id.print);
 //       btnGetLocation = (Button) findViewById(R.id.button);
  //      btnGetLocation.setOnClickListener(this);
///////////////////////
        //  locationManager = (LocationManager)
        //          getSystemService(Context.LOCATION_SERVICE);
    }

    public void nextPage(View v) {
        //      flag = displayGpsStatus();
 //       if (flag) {

   //         Log.v(TAG, "onClick");

            //   editLocation.setText("Please!! move your device to"+
            //          " see the changes in coordinates."+"\nWait..");

     //       pb.setVisibility(View.VISIBLE);
     //       locationListener = new MyLocationListener();

            //    locationManager.requestLocationUpdates(LocationManager
            //           .GPS_PROVIDER, 5000, 10, locationListener);


   //     } else {
  //          alertbox("Gps Status!!", "Your GPS is: OFF");
  //      }
        new PostData().execute();
    }

    /*----Method to Check GPS is enable or disable ----- */
    private Boolean displayGpsStatus() {
        ContentResolver contentResolver = getBaseContext()
                .getContentResolver();
        boolean gpsStatus = Settings.Secure
                .isLocationProviderEnabled(contentResolver,
                        LocationManager.GPS_PROVIDER);
        if (gpsStatus) {
            return true;

        } else {
            return false;
        }
    }

    /*----------Method to create an AlertBox ------------- */
    protected void alertbox(String title, String mymessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your Device's GPS is Disable")
                .setCancelable(false)
                .setTitle("** Gps Status **")
                .setPositiveButton("Gps On",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // finish the current activity
                                // AlertBoxAdvance.this.finish();
                                Intent myIntent = new Intent(
                                        android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                startActivity(myIntent);
                                dialog.cancel();
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // cancel the dialog box
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    /*----------Listener class to get coordinates ------------- */
    public class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location loc) {

            editLocation.setText("");
            pb.setVisibility(View.INVISIBLE);
            Toast.makeText(getBaseContext(), "Location changed : Lat: " +
                            loc.getLatitude() + " Lng: " + loc.getLongitude(),
                    Toast.LENGTH_SHORT).show();

            String longitude = "Longitude: " + loc.getLongitude();
            Log.v(TAG, longitude);
            String latitude = "Latitude: " + loc.getLatitude();
            Log.v(TAG, latitude);

            stop_long = loc.getLongitude();
            temp = Double.toString(stop_long);

            String s = longitude + "\n" + latitude;
            editLocation.setText(s);
            //     new JSONParse().execute();
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStatusChanged(String provider,
                                    int status, Bundle extras) {
            // TODO Auto-generated method stub
        }
    }

    public class PostData extends AsyncTask<String, String, JSONObject> {
        private ProgressDialog pDialog;
        protected void onPreExecute(){
        super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
            // do stuff before posting data

        }

        private static final String TAG_LAT = "lat";
        private static final String TAG_ARR = "array";
        private static final String TAG_LONG = "long";
        JSONArray array = null;


        @Override
        protected JSONObject doInBackground(String... urls) {
            JSONParser jParser = new JSONParser();
            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(url);
            return json;
        }


        @Override
        protected void onPostExecute(JSONObject json) {
            pDialog.dismiss();
         //   super.onPostExecute(json);
            try {
                String s="";
                String w="";
                JSONObject jsona = new JSONObject("{}");
               // JSONArray jArray = new JSONArray();
               // for(int i=0; i<jArray.length();i++){

                //    JSONObject jsono = jsona.getJSONObject();

                    s= (String)jsona.get("{lat}");
                    w= (String)jsona.get("{longi}");
              //  }

              //  array=json.getJSONArray(TAG_ARR);
               // JSONObject c =array.getJSONObject(0);

                //storing JSON item in a variable
           //     String lat = c.getString(TAG_LAT);
           //     String longi = c.getString(TAG_LONG);

                //set json data in textview
                tv.setText(s);
                tv1.setText(w);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }
}


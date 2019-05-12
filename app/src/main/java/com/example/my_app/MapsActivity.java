package com.example.my_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidhire.splashscreen.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private Marker marker;
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) this.getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        new AsyncTaskGetMarker().execute();
    }

    public String getJSONFromAssets() {
        String json = null;
        try {
            InputStream inputData = getAssets().open("classes.txt");
            int size = inputData.available();
            byte[] buffer = new byte[size];
            inputData.read(buffer);
            inputData.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private class AsyncTaskGetMarker extends AsyncTask<String , String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {
            String classesJsonString = getJSONFromAssets();
/*                JSONArray classesJsonArray = new JSONArray(classesJsonString);
                return classesJsonArray;
            } catch (JSONException e) {
                e.printStackTrace();
            }  */
            //This will only happen if an exception is thrown above:
            return classesJsonString;
        }

        protected void onPostExecute (String result){
            try {
                if (result != null) {
                    JSONObject Json = new JSONObject(result);
                    JSONArray JsonArr = Json.getJSONArray("features");
                    for (int i = 0; i < JsonArr.length(); i++) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = JsonArr.getJSONObject(i);
                            JSONObject jsongeometry = jsonObject.getJSONObject("geometry");
                            JSONArray jsonLatlng = jsongeometry.getJSONArray("coordinates");
                            JSONObject jsonProp = jsonObject.getJSONObject("properties");
                            String location = jsonProp.getString("Location");
                            String latitude = jsonLatlng.getString(1);
                            String longitude = jsonLatlng.getString(0);
                            String date = jsonProp.getString("Date");
                            String time = jsonProp.getString("Time");
                            String fees = jsonProp.getString("Fee");

                            drawMarker(new LatLng(Double.parseDouble(latitude),
                                    Double.parseDouble(longitude)), location, date, time, fees);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }


        private void drawMarker(LatLng point, String location, String date, String time, String fees) {

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(user_loc.getLatitude(), user_loc.getLongitude()))      // Sets the center of the map to location user
                        .zoom(17)                   // Sets the zoom
                        .build();                   // Creates a CameraPosition from the builder
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(point)
                    .title("Class Information")
                    .snippet("Location : " + location+"\nDate : "+date +"\nTime : "+time+"\nFees : "+fees));

            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

                @Override
                public View getInfoWindow(Marker arg0) {
                    return null;
                }

                @Override
                public View getInfoContents(Marker marker) {

                    Context context = getApplicationContext();

                    LinearLayout info = new LinearLayout(context);
                    info.setOrientation(LinearLayout.VERTICAL);

                    TextView title = new TextView(context);
                    title.setTextColor(Color.RED);
                    title.setGravity(Gravity.CENTER);
                    title.setTypeface(null, Typeface.BOLD);
                    title.setText(marker.getTitle());

                    TextView snippet = new TextView(context);
                    snippet.setTextColor(Color.GRAY);
                    snippet.setGravity(Gravity.CENTER);
                    snippet.setText(marker.getSnippet());

                    info.addView(title);
                    info.addView(snippet);

                    return info;
                }
            });
        }
    }

}
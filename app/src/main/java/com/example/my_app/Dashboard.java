package com.example.my_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.androidhire.splashscreen.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
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
                        String date = jsonProp.getString("Date");
                        String time = jsonProp.getString("Time");
                        String fees = jsonProp.getString("Fee");
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


}

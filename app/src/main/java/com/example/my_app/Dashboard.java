package com.example.my_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.androidhire.splashscreen.R;

import com.androidhire.splashscreen.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
     ListView listView;
    ArrayList<Info> infoArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        TextView textView= findViewById(R.id.textView);
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
                        String loc = jsonProp.getString("Location");
                        String dat = jsonProp.getString("Date");
                        String ti = jsonProp.getString("Time");
                        String fee = jsonProp.getString("Fee");
                        String ins = jsonProp.getString("Instructor");
                        Info info = new Info(loc,dat,ti,fee,ins);
                        //infoArrayList.add(info);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Info info2 = new Info("18 yash weed street 696969","12/4/2","2:20 pm","$90","Yashguy65");
                infoArrayList.add(info2);
                infoListAdapter adapter = new infoListAdapter(this,R.layout.single_list_item,infoArrayList);
                listView =  findViewById(R.id.list_view);
                listView.setAdapter(adapter);
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

    }



}

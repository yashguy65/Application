package com.example.my_app;

import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.androidhire.splashscreen.R;

import java.io.InputStream;
import java.util.List;

public class dashboard extends AppCompatActivity {
    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;
    private String[] scoreData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        listView = (ListView) findViewById(R.id.list_view);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext() , R.layout.single_list_item);
        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);
        InputStream inputStream = getResources().openRawResource(R.raw.addresslatlong);
        CsvReader csv  = new CsvReader(inputStream);
        List<String[]> list = csv.read();
        for (String[] scoreData : list){
            itemArrayAdapter.add(scoreData);}


    }}

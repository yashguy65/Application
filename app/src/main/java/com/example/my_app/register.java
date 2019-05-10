package com.example.my_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import  org.json.JSONArray;
import org.json.JSONException;
import  org.json.JSONObject;
import com.androidhire.splashscreen.R;
import org.json.*;


public class register extends AppCompatActivity implements View.OnClickListener {
    Button btnregister;
    EditText etUsername ,etPassword;
    String user , pass;
    TextView hello;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etUsername= (EditText) findViewById(R.id.etUsername);
        etPassword= (EditText) findViewById(R.id.etPassword);
        btnregister = (Button) findViewById(R.id.btnregister);
        btnregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnregister:
                startActivity(new Intent(this, MainActivity.class));
                String user = etUsername.getText().toString();
                String pass = etPassword.getText().toString();
                JSONObject obj = new JSONObject();
                try {
                    obj.put("Username",user);
                    obj.put("Password",pass);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                System.out.println(pass+ "  " +user);
                Log.d("create",user);
                break;

        }}
    }


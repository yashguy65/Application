package com.example.my_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androidhire.splashscreen.R;

public class register extends AppCompatActivity implements View.OnClickListener {
    Button btnregister;
    EditText etUsername ,etPassword;
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



                break;
        }
    }
}

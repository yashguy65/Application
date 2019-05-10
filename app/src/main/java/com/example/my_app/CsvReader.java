package com.example.my_app;

import android.renderscript.ScriptGroup;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;

public class CsvReader {
    InputStream inputStream;
    public  CsvReader(InputStream is) {
        this.inputStream = is;
    }
    public List<String[]> read(){
        List<String[]> resultList = new ArrayList<String[]>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try{
            String csvline;
            while ((csvline=reader.readLine()) != null){
                String[] row = csvline.split(",");
                resultList.add(row);
            }
        } catch (IOException ex){
            throw new RuntimeException("Error in reading the file"+ ex);
        } finally {
            try {
                inputStream.close();
            }catch (IOException e){
                throw new RuntimeException("Error while closing inputStream"+ e);

            }
        }

    return null;
    }

}

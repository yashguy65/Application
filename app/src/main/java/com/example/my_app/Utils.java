package com.example.my_app;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    public static boolean checkLoginCredentials(String result, String userName, String password){

        boolean status = false;
        String md5UserName = getMd5(userName);
        String md5Password = getMd5(password);

            try{
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length() ; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if( jsonObject.getString("username").equals(md5UserName) &&  jsonObject.getString("password").equals(md5Password)  ) {
                            status = true;
                        break;
                    }
                }
            }catch (JSONException je){
                je.printStackTrace();
            }

        return status;
    }
    public static String getJSONFromAssets(Context context) {
        String re = null;
        try {

            InputStream inputData = context.getAssets().open("user.txt");
            int size = inputData.available();
            byte[] buffer = new byte[size];
            inputData.read(buffer);
            inputData.close();
            re = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return re;
    }
    private static String getMd5(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext.toUpperCase();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}

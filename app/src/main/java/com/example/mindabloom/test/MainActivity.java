package com.example.mindabloom.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mindabloom.test.Database.MarvelDatabaseHelper;
import com.example.mindabloom.test.Helpers.PropertiesReader;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class MainActivity extends AppCompatActivity {


    private PropertiesReader propertiesReader;
    private Properties properties;
    private MarvelDatabaseHelper marvelDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        propertiesReader = new PropertiesReader(this);
        properties = propertiesReader.getProperties("test.properties");
        marvelDatabaseHelper = new MarvelDatabaseHelper(this);

        Date date = new Date();

        Log.d("Prop", properties.getProperty("propTest1"));
        long ts = date.getTime();

        Log.d("TS", ts + "");

        String hash = md5(String.valueOf(ts) + "e17ce89b0134ae7d7333be79c9121c5526269d88" + "2dc5472f154dbb57b42cea72d76b0e5f");

        Log.d("Hash", hash);

        marvelDatabaseHelper.addName("ahmed");
        marvelDatabaseHelper.addName("mohamed");

        List<String> myNames = new ArrayList<String>();
        myNames = marvelDatabaseHelper.getHistory();

        for(String s : myNames){
            Log.d("Row", s);
        }

    }

    public static String md5(String s) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes(Charset.forName("US-ASCII")), 0, s.length());
            byte[] magnitude = digest.digest();
            BigInteger bi = new BigInteger(1, magnitude);
            String hash = String.format("%0" + (magnitude.length << 1) + "x", bi);
            return hash;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}



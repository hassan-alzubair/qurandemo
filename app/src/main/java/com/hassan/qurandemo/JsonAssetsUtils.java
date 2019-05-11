package com.hassan.qurandemo;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class JsonAssetsUtils {

    public static JSONObject getJsonObjectFromAssets(Context context,String filename)
    {
        try {
            return new JSONObject(getJsonFromAssets(context,filename));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray getJsonArrayFromAssets(Context context, String filename)
    {
        try {
            return new JSONArray(getJsonFromAssets(context,filename));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getJsonFromAssets(Context context,String filename)
    {
        try {
            InputStream inputStream = context.getAssets().open(filename);
            int bufferSize = inputStream.available();
            byte[] buffer = new byte[bufferSize];
            inputStream.read(buffer);
            String json = new String(buffer,"UTF-8");
            return json;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

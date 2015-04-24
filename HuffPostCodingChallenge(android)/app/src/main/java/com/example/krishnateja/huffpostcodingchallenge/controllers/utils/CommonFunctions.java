package com.example.krishnateja.huffpostcodingchallenge.controllers.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.example.krishnateja.huffpostcodingchallenge.models.AppPreferences;
import com.example.krishnateja.huffpostcodingchallenge.models.RequestParams;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by krishnateja on 4/24/2015.
 */
public class CommonFunctions {
    public static boolean isConnected(Context context) {
        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isInternetReachable() {
        java.net.InetAddress address = null;
        boolean canConnect = false;
        try {
            address = InetAddress.getByName("www.google.com");
            if (!address.equals("")) {
                canConnect = true;
                return canConnect;
            } else {
                canConnect = false;
                return canConnect;
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
            canConnect = false;
            return canConnect;

        }
    }

    public static RequestParams buildParams(String[] path,HashMap<String,String> getVariables,Context context){
        RequestParams params=new RequestParams();
        Uri.Builder url=new Uri.Builder();
        url.scheme(AppPreferences.ServerConstants.SCHEME)
           .authority(AppPreferences.ServerConstants.AUTHORITY).build();
        url.appendPath(path[0]);
        url.appendPath(path[1]);
        if(getVariables!=null) {
            for (Map.Entry<String, String> entry : getVariables.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                url.appendQueryParameter(key, value);
            }
        }
        url.build();
        params.setURI(url.toString());
        params.setMethod(AppPreferences.ServerConstants.METHOD);
        params.setContext(context);
        return params;
    }
}

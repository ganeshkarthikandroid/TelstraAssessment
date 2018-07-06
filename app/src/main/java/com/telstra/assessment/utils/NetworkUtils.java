package com.telstra.assessment.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public final class NetworkUtils {
    /*
     Checking internet connection
     */
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (null != connectivityManager) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return null != activeNetworkInfo && activeNetworkInfo.isConnected();
    }
}

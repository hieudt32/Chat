package vn.iotech.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * NetworkUtils
 * Created by akai on 12/21/2017.
 */

public class NetworkUtils {

    public NetworkUtils() {

    }

    public static boolean isNetworkAvaiable(Activity activity) {
        ConnectivityManager connectivityMng = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityMng.getActiveNetworkInfo();
        // networkInfo will be null if no network is available
        return networkInfo != null && networkInfo.isConnected();
    }

    public static boolean is3GOn(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) { //connected internet
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                return false;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                return true;
            }
        }
        return false;
    }
}

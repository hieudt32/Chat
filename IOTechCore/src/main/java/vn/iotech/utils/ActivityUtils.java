package vn.iotech.utils;

import android.app.Activity;
import android.content.Intent;

/**
 * ActivityUtils
 * Created by akai on 12/21/2017.
 */

public class ActivityUtils {
    public ActivityUtils() {

    }


    public static void restartApp(Activity context) {
        Intent i = context.getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(context.getBaseContext().getPackageName());
        assert i != null;
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }
}

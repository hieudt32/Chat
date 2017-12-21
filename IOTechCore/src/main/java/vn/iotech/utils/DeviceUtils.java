package vn.iotech.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import vn.iotech.base.log.Logger;

/**
 * DeviceUtils
 * Created by akai on 12/21/2017.
 */

public class DeviceUtils {

    public DeviceUtils() {

    }

    public static Point getScreenSize(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public static void openAppInStore(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            Logger.e("Error", "ActivityNotFoundException", anfe);
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public static void call(Context context, String numberPhone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", numberPhone, null));
        context.startActivity(intent);
    }

    public static void sendEmail(Context context, String email) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null));
        context.startActivity(intent);
    }

    public static void sendSMS(Context context, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms", phoneNumber, null));
        context.startActivity(intent);
    }

    public static void dismissKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    /**
     * Show keyboard for {@link EditText}
     */
    public void showKeyboard(EditText editText, Activity activity) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    /**
     * Force show keyboard
     */
    public void forceShowKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

}

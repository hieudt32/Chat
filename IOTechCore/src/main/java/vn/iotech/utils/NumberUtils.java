package vn.iotech.utils;

import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * NumberUtils
 * Created by akai on 12/21/2017.
 */

public class NumberUtils {

    public NumberUtils() {
    }

    public static String formatNumber(double number) {
        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        formatter.applyPattern("#,###,###,###.##");
        return formatter.format(number);
    }

    public static String formatNumberComma(double number) {
        DecimalFormat formatter = new DecimalFormat("#,###,###,###.00", new DecimalFormatSymbols(Locale.US));
        return formatter.format(number);
    }

    public static void formatDoubleEditTextRunTime(EditText mEditText, double MAX) {
        try {
            String originalString = mEditText.getText().toString();
            if (originalString.contains(",")) originalString = originalString.replaceAll(",", "");
            if (originalString.contains(".")) {
                int index = originalString.indexOf(".");
                if (index == originalString.length() - 1) return;
            }
            double doubleValue = Double.parseDouble(originalString);
            if (doubleValue > MAX) {
                if (doubleValue % 1 == 0) {
                    doubleValue = MAX - 0.99;
                } else {
                    doubleValue = MAX;
                }
            }
            String formattedString = formatNumber(doubleValue);
            mEditText.setText(formattedString);
            mEditText.setSelection(mEditText.getText().length());
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    public static void formatIntegerEditTextRunTime(EditText mEditText, long MAX) {
        try {
            String originalString = mEditText.getText().toString();
            if (originalString.contains(",")) originalString = originalString.replaceAll(",", "");

            long longValue = Long.parseLong(originalString);
            longValue = longValue > MAX ? MAX : longValue;
            String formattedString = formatNumber(longValue);
            mEditText.setText(formattedString);
            mEditText.setSelection(mEditText.getText().length());
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    public static int getInteger(String s) {
        try {
            if (s.contains(",")) s = s.replaceAll(",", "");
            return Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    public static double getDouble(String s) {
        try {
            if (s.contains(",")) s = s.replaceAll(",", "");
            return Double.parseDouble(s);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }

    public static float getFloat(String s) {
        try {
            if (s.contains(",")) s = s.replaceAll(",", "");
            return Float.parseFloat(s);
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }
}

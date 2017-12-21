package vn.iotech.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * StringUtils
 * Created by akai on 12/21/2017.
 */

public class StringUtils {
    public StringUtils() {

    }

    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    /**
     * method is used for checking valid email id format.
     *
     * @param email
     * @return boolean true for valid false for invalid
     */
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * method is used for checking valid password id format.
     *
     * @param password
     * @return boolean true for valid false for invalid
     */
    public static boolean isPasswordValid(String password) {
        String expression = "^(?=.*[0-9])(?=.*[a-z]).{8,}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        return pattern.matcher(password).matches();
    }


}

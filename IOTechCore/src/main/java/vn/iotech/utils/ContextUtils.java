package vn.iotech.utils;

import android.app.Activity;
import android.content.Context;

/**
 * Context utils
 * Created by neo on 7/18/2016.
 */
public class ContextUtils {

  private ContextUtils() {

  }

  /**
   * Check validation of context
   */
  public static boolean isValidContext(Context context) {
    if (context == null) {
      return false;
    }
    return !(context instanceof Activity
            && ((Activity) context).isFinishing());
  }
}

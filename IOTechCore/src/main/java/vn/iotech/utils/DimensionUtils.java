package vn.iotech.utils;

import android.content.Context;

/**
 * DimensionUtils
 * Created by akai on 12/21/2017.
 */

public class DimensionUtils {
    public DimensionUtils() {

    }
    /**
     * convert dp to <b>pixel</b>.
     *
     * @param context target context
     * @param dp      to convert.
     * @return Dimension in pixel.
     */
    public float dpTOpx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    /**
     * convert pixel to <b>dp</b>.
     *
     * @param context target context
     * @param px      to convert.
     * @return Dimension in dp.
     */
    public float pxTOdp(Context context, float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }
}

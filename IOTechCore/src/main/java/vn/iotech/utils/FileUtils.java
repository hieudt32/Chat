package vn.iotech.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.util.Date;

/**
 * FileUtils
 * Created by akai on 12/21/2017.
 */

public class FileUtils {
    public FileUtils() {

    }

    public static String getFileExtension(String filePath) {
        return filePath.substring(filePath.lastIndexOf(".") + 1);
    }

    public static String getPathTempFile(Context context, String extension) {
        Date currentDate = new Date(System.currentTimeMillis());
        return getAppImagePath(context) + "/" + currentDate.getTime() + "." + extension;
    }

    private static String getAppImagePath(Context context) {
        return context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath();
    }
}

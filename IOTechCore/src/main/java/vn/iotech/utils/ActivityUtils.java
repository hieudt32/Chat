package vn.iotech.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import vn.iotech.Default;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * ActivityUtils
 * Created by akai on 12/21/2017.
 */

public class ActivityUtils {
    public ActivityUtils() {

    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation
     * is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {

        addFragmentToActivity(fragmentManager, fragment, frameId, false, null);
    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation
     * is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId, boolean
                                                     addToBackStack, String tag) {
        addFragmentToActivity(fragmentManager, fragment, frameId,
                addToBackStack, tag, false);
    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation
     * is
     * performed by the {@code fragmentManager}.
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId, boolean
                                                     addToBackStack, String tag, boolean loadExisted) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }

        if (loadExisted) {
            final Fragment existingFragment = fragmentManager.findFragmentByTag(tag);
            if (existingFragment != null) {
                for (Fragment f : fragmentManager.getFragments()) {
                    transaction.hide(f);
                }
                transaction.show(existingFragment);
//                if (existingFragment instanceof ViewFragment) {
//                    new Handler().post(new Runnable() {
//                        @Override
//                        public void run() {
//                            ((ViewFragment) existingFragment).getPresenter().onFragmentDisplay();
//                        }
//                    });
//                }
            } else {
                transaction.add(frameId, fragment, tag);
            }
        } else {
            transaction.add(frameId, fragment, tag);
        }
        transaction.commit();
    }

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation
     * is
     * performed by the {@code fragmentManager}.
     */
    public static void addChildFragment(@NonNull FragmentManager fragmentManager,
                                        @NonNull Fragment fragment, int frameId,
                                        boolean addToBackStack, String tag) {
        checkNotNull(fragmentManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        if (addToBackStack) {
            transaction.addToBackStack(tag);
        }
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void addChildFragment(FragmentManager fragmentManager, Fragment fragment,
                                        int rootFrameId, boolean addToBackStack) {
        addChildFragment(fragmentManager, fragment, rootFrameId, addToBackStack,
                fragment.getClass().getSimpleName());
    }

    /**
     * Start activity
     */
    public static <T extends Activity> void startActivity(Context context, Class<T> clazz) {
        context.startActivity(new Intent(context, clazz));
    }

    /**
     * Start activity for result
     */
    public static <T extends Activity> void startActivityForResult(Activity activity, Class<T> clazz, int requestCode) {
        activity.startActivityForResult(new Intent(activity, clazz), requestCode);
    }

    /**
     * Start activity with extras
     */
    public static <T extends Activity> void startActivity(Context context, Class<T> clazz,
                                                          Bundle extras) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(extras);
        context.startActivity(intent);
    }

    /**
     * Start activity with extras for result
     */
    public static <T extends Activity> void startActivityForResult(Activity activity, Class<T> clazz,
                                                                   Bundle extras, int requestCode) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtras(extras);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * Start activity with extras
     */
    public static <T extends Activity> void startActivity(Context context, Class<T> clazz,
                                                          Bundle extras, boolean withAnim) {
        Intent intent = new Intent(context, clazz);
        intent.putExtras(extras);
        context.startActivity(intent);

        if (context instanceof Activity && withAnim) {
            ((Activity) context).overridePendingTransition(Default.ANIM_IN,
                    Default.ANIM_OUT);
        }
    }


    public static void restartApp(Activity context) {
        Intent i = context.getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(context.getBaseContext().getPackageName());
        assert i != null;
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(i);
    }
}

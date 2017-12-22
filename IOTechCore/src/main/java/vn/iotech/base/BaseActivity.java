package vn.iotech.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.util.List;

import butterknife.ButterKnife;

/**
 * BaseActivity
 * Created by akai on 12/21/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public void initLayout() {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initLayout();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected abstract int getLayoutId();

    /**
     * Hide keyboard of current focus view
     */
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm =
                    (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
    }

    public static Fragment getTopFragment(FragmentManager manager) {
        if (manager == null) {
            return null;
        }
        if (manager.getBackStackEntryCount() > 0) {
            String fragmentName = manager.getBackStackEntryAt(manager.getBackStackEntryCount() - 1).getName();
            List<Fragment> fragments = manager.getFragments();
            if (fragments != null && !fragments.isEmpty()) {
                Fragment topFragment = null;
                int i = 1;
                while (i < fragments.size() &&
                        (topFragment == null || !isSameClass(topFragment, fragmentName))) {

                    topFragment = fragments.get(fragments.size() - i);
                    i++;
                }
                return topFragment;
            }
        } else {
            List<Fragment> fragments = manager.getFragments();
            if (fragments != null && !fragments.isEmpty()) {
                return fragments.get(0);
            }
        }
        return null;
    }

    private static boolean isSameClass(Fragment topFragment, String fragmentName) {
        String simpleName = topFragment.getClass().getSimpleName();
        return simpleName.equals(fragmentName);
    }
}

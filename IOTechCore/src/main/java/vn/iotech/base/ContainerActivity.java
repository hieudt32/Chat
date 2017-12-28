package vn.iotech.base;

import android.support.v4.app.FragmentManager;

import vn.iotech.base.viper.interfaces.ContainerView;
import vn.iotech.base.viper.interfaces.IView;

/**
 * ContainerActivity
 * Created by akai on 12/22/2017.
 */

public abstract class ContainerActivity extends BaseActivity implements ContainerView {

    @Override
    public void back(int count) {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            for (int i = 0; i < count; i++) {
                manager.popBackStack();
            }
        } else {
            finish();
        }
    }

    @Override
    public void loadView(IView view, int frameId) {

    }


}

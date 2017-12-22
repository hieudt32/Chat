package vn.iotech.base;

import android.support.v4.app.FragmentManager;

import vn.iotech.base.viper.interfaces.ContainerView;
import vn.iotech.base.viper.interfaces.IView;

/**
 * ContainerFragment
 * Created by akai on 12/22/2017.
 */

public abstract class ContainerFragment extends BaseFragment implements ContainerView {

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void pushView(IView view) {

    }

    @Override
    public void back(int count) {
        FragmentManager manager = getFragmentManager();
        if (manager.getBackStackEntryCount() > 0) {
            for (int i = 0; i < count; i++) {
                manager.popBackStack();
            }
        } else {
            getActivity().finish();
        }
    }
}

package vn.iotech.base.viper.interfaces;

import android.support.v4.app.FragmentManager;

import vn.iotech.base.viper.ViewFragment;

/**
 * ContainerView
 * Created by akai on 12/22/2017.
 */

public interface ContainerView extends IView {

    ViewFragment getViewFragment();

    void addView(IView view);

    void pushView(IView view);

    void popView(IView mView);

    void pushView(IView view, int frameId);

    void loadView(IView view, int frameId);

    void loadChildView(IView view, int frameId, FragmentManager childFragmentManager);

    void pushChildView(IView view, int frameId, FragmentManager childFragmentManager);

    void presentView(IView view);

    void back(int count);
}

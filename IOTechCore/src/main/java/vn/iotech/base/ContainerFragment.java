package vn.iotech.base;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import vn.iotech.Default;
import vn.iotech.R;
import vn.iotech.base.viper.ViewFragment;
import vn.iotech.base.viper.interfaces.ContainerView;
import vn.iotech.base.viper.interfaces.IPresenter;
import vn.iotech.base.viper.interfaces.IView;

/**
 * ContainerFragment
 * Created by akai on 12/22/2017.
 */

public abstract class ContainerFragment extends BaseFragment implements ContainerView {

    @Override
    protected int getLayoutId() {
        return R.layout.container_frame;
    }

    @Override
    public void initLayout() {

    }

    @Override
    public void pushView(IView view) {
        if (view instanceof BaseFragment) {
            addChildFragment(((BaseFragment) view)
                            .setAnimIn(R.anim.slide_right_in)
                            .setAnimOut(R.anim.slide_right_out),
                    true);
        }

    }

    @Override
    protected boolean needTranslationAnimation() {
        return true;
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

    @Override
    public void popView(IView mView) {
        if (mView instanceof BaseFragment) {
            FragmentManager manager = getChildFragmentManager();
            Fragment existingFragment = manager.findFragmentByTag(mView.getClass().getSimpleName());
            if (existingFragment != null) {
                manager.beginTransaction().remove(existingFragment).commit();
            }
        }
    }

    public void addChildFragment(Fragment fragment, boolean addToBackStack) {
        addChildFragment(fragment, addToBackStack, fragment.getClass().getSimpleName());
    }

    public void addChildFragment(Fragment fragment, boolean addToBackStack, String tag) {
        addChildFragment(fragment, Default.FRAGMENT_CONTAINER_ID, addToBackStack, tag);
    }

    @Override
    public void pushView(IView view, int frameId) {
        if (view instanceof BaseFragment) {
            addChildFragment((BaseFragment) view, frameId, false, view.getClass().getSimpleName());
        }
    }

    @Override
    public void loadView(IView view, int frameId) {
        if (view instanceof BaseFragment) {
            addChildFragment((BaseFragment) view, frameId, false, view.getClass().getSimpleName());
        }
    }

    @Override
    protected void startPresent() {
        ViewFragment viewFragment = onCreateFirstFragment();
        if (viewFragment != null) {
            addChildFragment(viewFragment, false);
        }
        mIsStarted = true;

    }

    @Override
    public Activity getViewContext() {
        return getActivity();
    }

    @Override
    public IPresenter getPresenter() {
        return null;
    }
}

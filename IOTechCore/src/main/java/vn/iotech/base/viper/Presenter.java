package vn.iotech.base.viper;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import vn.iotech.base.viper.interfaces.ContainerView;
import vn.iotech.base.viper.interfaces.IInteractor;
import vn.iotech.base.viper.interfaces.IPresenter;
import vn.iotech.base.viper.interfaces.IView;
import vn.iotech.eventbus.EventBusWrapper;

/**
 * Presenter
 * Created by akai on 12/22/2017.
 */

public abstract class Presenter<V extends IView, I extends IInteractor>
        implements IPresenter<V, I> {

    protected ContainerView mContainerView;
    protected I mInteractor;
    protected V mView;

    @SuppressWarnings("unchecked")
    public Presenter(ContainerView containerView) {
        mContainerView = containerView;
        mInteractor = onCreateInteractor();
        mView = onCreateView();
        mView.setPresenter(this);
    }

    @Override
    public Activity getViewContext() {
        return mView.getViewContext();
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public I getIntoractor() {
        return mInteractor;
    }

    @Override
    public Fragment getFragment() {
        return getView() instanceof Fragment ? (Fragment) getView() : null;
    }

    @Override
    public void pushView() {
        mView.getBaseActivity().hideKeyboard();
        mContainerView.pushView(mView);
    }

    @Override
    public void presentView() {
        mContainerView.presentView(mView);
    }

    @Override
    public void pushChildView(int frameId, FragmentManager childFragmentManager) {
        mContainerView.pushChildView(mView, frameId, childFragmentManager);
    }

    @Override
    public void loadChildView(int frameId, FragmentManager childFragmentManager) {
        mContainerView.loadChildView(mView, frameId, childFragmentManager);
    }

    @Override
    public void registerEventBus() {
        EventBusWrapper.register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBusWrapper.unregister(this);
    }


    @Override
    public void back(int count) {
        mView.getBaseActivity().hideKeyboard();
        mContainerView.back(count);
    }

    @Override
    public boolean isViewShown() {
        return mView.isShown();
    }
}

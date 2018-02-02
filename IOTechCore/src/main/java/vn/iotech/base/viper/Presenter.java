package vn.iotech.base.viper;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
  protected V mView;
  protected I mInteractor;

  @SuppressWarnings("unchecked")
  public Presenter(ContainerView containerView) {
    mContainerView = containerView;
    mInteractor = onCreateInteractor();
    mView = onCreateView();

    mView.setPresenter(this);
  }

  public Activity getViewContext() {
    return mView.getViewContext();
  }

  @Override
  public V getView() {
    return mView;
  }

  @Override
  public I getInteractor() {
    return mInteractor;
  }

  @Override
  public Fragment getFragment() {
    return getView() instanceof Fragment ? (Fragment) getView() : null;
  }

  @Override
  public void presentView() {
    mContainerView.presentView(mView);
  }

  @Override
  public void pushView() {
    mContainerView.pushView(mView);
  }

  @Override
  public void pushChildView(int frameId, FragmentManager childFragmentManager) {
    if (getFragment() != null) {
      mContainerView.pushChildView(mView, frameId, childFragmentManager);
    }
  }

  @Override
  public void loadChildView(int frameId, FragmentManager childFragmentManager) {
    if (getFragment() != null) {
      mContainerView.loadChildView(mView, frameId, childFragmentManager);
    }
  }

  public void addView() {
    mContainerView.addView(mView);
  }

  // Event bus

  @Override
  public void registerEventBus() {
    EventBusWrapper.register(this);
  }

  @Override
  public void unregisterEventBus() {
    EventBusWrapper.unregister(this);
  }

  @Subscribe(threadMode = ThreadMode.BACKGROUND)
  public void onMessageEvent(NoneEvent event) {
    // Default event handler
  }

  public interface NoneEvent {
    // Default event
  }

  @Override
  public void back(int count) {
    mView.getBaseActivity().hideKeyboard();
    mContainerView.back(count);
  }

  @Override
  public void onFragmentDisplay() {

  }

  @Override
  public boolean isViewShown() {
    return ((ViewFragment) mView).isShown();
  }
}

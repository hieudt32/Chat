package vn.iotech.base.viper.interfaces;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * IPresenter
 * Created by akai on 12/22/2017.
 */

public interface IPresenter<V extends IView, I extends IInteractor> {

  void start();

  V getView();

  V onCreateView();

  I getInteractor();

  I onCreateInteractor();

  Fragment getFragment();

  void presentView();

  void pushView();

  void pushChildView(int frameId, FragmentManager childFragmentManager);

  void loadChildView(int frameId, FragmentManager childFragmentManager);

  Activity getViewContext();

  void registerEventBus();

  void unregisterEventBus();

  void back(int count);

  void onFragmentDisplay();

  boolean isViewShown();

}

package vn.iotech.base.viper;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.iotech.base.BaseActivity;
import vn.iotech.base.BaseFragment;
import vn.iotech.base.viper.interfaces.IView;

/**
 * ViewFragment
 * Created by akai on 12/22/2017.
 */

public abstract class ViewFragment<P extends Presenter> extends BaseFragment implements IView<P> {

    protected P mPresenter;
    protected boolean mIsInitialized = false;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mPresenter == null) {
            Intent i = getActivity().getBaseContext().getPackageManager()
                    .getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            return;
        }
        mPresenter.registerEventBus();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unregisterEventBus();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (!mIsInitialized) {
            mRootView = super.onCreateView(inflater, container, savedInstanceState);
            initLayout();
            mIsInitialized = true;
        }
        return mRootView;
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void initLayout() {

    }

    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public boolean isShown() {
        return isResumed() && this == BaseActivity.getTopFragment(getViewFragmentManager());
    }
}

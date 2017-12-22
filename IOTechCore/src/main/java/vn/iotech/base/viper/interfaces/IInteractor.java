package vn.iotech.base.viper.interfaces;

/**
 * IInteractor
 * Created by akai on 12/22/2017.
 */

public interface IInteractor<P extends IPresenter> {
    P getPresenter();
}

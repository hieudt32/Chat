package vn.iotech.test;

import vn.iotech.base.viper.Presenter;
import vn.iotech.base.viper.interfaces.ContainerView;

/**
 * The test Presenter
 */
public class testPresenter extends Presenter<testContract.View, testContract.Interactor>
        implements testContract.Presenter {

  public testPresenter(ContainerView containerView) {
    super(containerView);
  }

  @Override
  public testContract.View onCreateView() {
    return testFragment.getInstance();
  }

  @Override
  public void start() {
    // Getting data here
  }

  @Override
  public testContract.Interactor onCreateInteractor() {
    return new testInteractor(this);
  }
}

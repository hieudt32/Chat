package vn.iotech.test;

import vn.iotech.base.viper.Interactor;

/**
 * The test interactor
 */
class testInteractor extends Interactor<testContract.Presenter>
        implements testContract.Interactor {

  testInteractor(testContract.Presenter presenter) {
    super(presenter);
  }
}

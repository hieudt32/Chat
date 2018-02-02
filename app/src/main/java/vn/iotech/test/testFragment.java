package vn.iotech.test;

import vn.iotech.base.viper.ViewFragment;
import vn.iotech.firebasechat.R;

/**
 * The test Fragment
 */
public class testFragment extends ViewFragment<testContract.Presenter> implements testContract.View {

  public static testFragment getInstance() {
    return new testFragment();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.fragment_test;
  }
}

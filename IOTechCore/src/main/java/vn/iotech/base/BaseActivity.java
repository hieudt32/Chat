package vn.iotech.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * BaseActivity
 * Created by akai on 12/21/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

  public void initLayout() {

  }

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutId());
    ButterKnife.bind(this);
    initLayout();

  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  protected abstract int getLayoutId();


}

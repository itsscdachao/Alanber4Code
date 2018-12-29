package com.itssc.alanber4code.base.activity;

import android.app.Activity;
import android.os.Bundle;

import com.itssc.alanber4code.base.presenter.BaseCommonPresenter;
import com.itssc.alanber4code.base.view.IBaseView;

/**
 * 普通样式Activity
 * @param <T>
 */
public abstract class BaseCommonActivity<T extends BaseCommonPresenter> extends Activity implements IBaseActivity, IBaseView {

    public T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mPresenter = createPresenter();
        mPresenter.attachView(this);
    }

    protected abstract int getLayoutResId();

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}

package com.itssc.tool_base.base_mvp.activity;

import android.app.Activity;
import android.os.Bundle;

import com.itssc.tool_base.base_mvp.fragment.IBaseFragment;
import com.itssc.tool_base.base_mvp.presenter.BaseListPresenter;
import com.itssc.tool_base.base_mvp.view.IBaseView;

/**
 * 列表样式Activity
 *
 * @param <T>
 */
public abstract class BaseListActivity<T extends BaseListPresenter> extends Activity implements IBaseFragment, IBaseView {

    private T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected abstract int getLayoutResId();

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}

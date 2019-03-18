package com.itssc.tool_base.base_mvp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.itssc.tool_base.base_mvp.presenter.BaseListPresenter;
import com.itssc.tool_base.base_mvp.view.IBaseView;

/**
 * 列表样式Activity
 *
 * @param <T>
 */
public abstract class BaseListActivity<T extends BaseListPresenter> extends AppCompatActivity implements IBaseActivity, IBaseView {

    private T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这句代码必须写在setContentView()方法的前面,隐藏AppCompatActivity标题栏
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutResId());
        initViewAndData();
//        mPresenter = createPresenter();
//        if (mPresenter != null) {
//            mPresenter.attachView(this);
//        }
    }

    protected abstract int getLayoutResId();

    protected abstract void initViewAndData();

    protected abstract T createPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (mPresenter != null) {
//            mPresenter.detachView();
//        }
    }
}

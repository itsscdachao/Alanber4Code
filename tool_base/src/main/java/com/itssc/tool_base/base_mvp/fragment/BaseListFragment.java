package com.itssc.tool_base.base_mvp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.itssc.tool_base.base_mvp.presenter.BaseListPresenter;
import com.itssc.tool_base.base_mvp.view.IBaseView;


/**
 * 列表样式Fragment
 *
 * @param <T>
 */
public abstract class BaseListFragment<T extends BaseListPresenter> extends Fragment implements IBaseFragment, IBaseView {

    private T mPresenter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    public abstract T createPresenter();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

package com.itssc.alanber4code.base.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.itssc.alanber4code.base.presenter.BaseCommonPresenter;
import com.itssc.alanber4code.base.view.IBaseView;

/**
 * 普通样式Fragment
 * @param <T>
 */
public abstract class BaseCommonFragment<T extends BaseCommonPresenter> extends Fragment implements IBaseFragment, IBaseView {

    public T mPresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView(this);
    }

    public abstract T createPresenter();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

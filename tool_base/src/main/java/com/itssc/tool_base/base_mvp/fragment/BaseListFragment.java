package com.itssc.tool_base.base_mvp.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.itssc.tool_base.base_mvp.presenter.BaseListPresenter;
import com.itssc.tool_base.base_mvp.view.IBaseView;


/**
 * 列表样式Fragment
 *
 * @param <T>
 */
public abstract class BaseListFragment<T extends BaseListPresenter> extends Fragment implements IBaseFragment, IBaseView {
    public T mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        mPresenter = createPresenter();
//        if (mPresenter != null) {
//            mPresenter.attachView(this);
//        }
        View view = inflater.inflate(getLayoutResId(), container, false);
        initViewAndData(view);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract int getLayoutResId();

    protected abstract void initViewAndData(View view);

    protected abstract T createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if (mPresenter != null) {
//            mPresenter.detachView();
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

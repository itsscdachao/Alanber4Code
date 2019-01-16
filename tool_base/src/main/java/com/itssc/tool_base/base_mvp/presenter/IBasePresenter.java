package com.itssc.tool_base.base_mvp.presenter;

import com.itssc.tool_base.base_mvp.view.IBaseView;

/**
 * MVP的P层,定义最基本且共有的Presenter方法
 */
public interface IBasePresenter<T extends IBaseView> {
    void attachView(T view);
    void detachView();
}

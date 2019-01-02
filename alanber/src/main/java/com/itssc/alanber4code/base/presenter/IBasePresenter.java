package com.itssc.alanber4code.base.presenter;

import com.itssc.alanber4code.base.view.IBaseView;

/**
 * MVP的P层,定义最基本且共有的Presenter方法
 */
public interface IBasePresenter<T extends IBaseView> {
    void attachView(T view);
    void detachView();
}

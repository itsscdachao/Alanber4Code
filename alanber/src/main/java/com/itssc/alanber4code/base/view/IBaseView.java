package com.itssc.alanber4code.base.view;

/**
 * MVP之V层 是所有view的基类,定义最为通用的view层方法
 */
public interface IBaseView {
    void showLoadingView();
    void hideLoadingView();

    void showEmptyLayout();
    void hideEmptyLayout();
}

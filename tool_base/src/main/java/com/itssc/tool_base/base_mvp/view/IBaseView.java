package com.itssc.tool_base.base_mvp.view;

/**
 * MVP之V层 是所有view的基类,定义最为通用的view层方法
 */
public interface IBaseView {
    void showPageLoadingView();
    void hidePageLoadingView();

    void showPageEmptyLayout();
    void hidePageEmptyLayout();

    void showPageErrorLayout();
    void hidePageErrorLayout();
}

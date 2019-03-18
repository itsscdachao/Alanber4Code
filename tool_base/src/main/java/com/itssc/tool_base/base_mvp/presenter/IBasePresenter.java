package com.itssc.tool_base.base_mvp.presenter;

import com.itssc.tool_base.base_mvp.model.IBaseModel;
import com.itssc.tool_base.base_mvp.view.IBaseView;

/**
 * MVP的P层,定义最基本且共有的Presenter方法
 */
public interface IBasePresenter<M extends IBaseModel, V extends IBaseView> {
    /**
     * 绑定Model层
     *
     * @param model
     */
    void registerModel(M model);

    /**
     * 解绑Model层
     */
    void unRegisterModel();

    /**
     * 绑定View层
     *
     * @param view
     */
    void registerView(V view);

    /**
     * 解绑View层
     */
    void unRegisterView();
}

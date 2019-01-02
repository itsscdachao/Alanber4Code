package com.itssc.alanber4code;

import com.itssc.alanber4code.base.activity.BaseCommonActivity;
import com.itssc.alanber4code.base.presenter.BaseCommonPresenter;

public class HomeActivity extends BaseCommonActivity {

    @Override
    protected BaseCommonPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void showEmptyLayout() {

    }

    @Override
    public void hideEmptyLayout() {

    }
}

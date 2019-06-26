package com.itssc.alanber4code;

import android.os.Bundle;
import android.widget.Toast;

import com.itssc.alanber4code.base.activity.BaseCommonActivity;
import com.itssc.alanber4code.base.presenter.BaseCommonPresenter;
import com.itssc.tool_widget.commonview.PasswordInputView;

public class PasswordViewTestActivity extends BaseCommonActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_password_view_test;
    }

    @Override
    protected BaseCommonPresenter createPresenter() {
        return null;
    }

    @Override
    protected void init() {
        PasswordInputView passwordView = findViewById(R.id.passwordView);
        passwordView.setInputListener(text -> Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show());
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

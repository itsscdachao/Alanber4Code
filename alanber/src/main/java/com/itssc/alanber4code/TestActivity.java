package com.itssc.alanber4code;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.itssc.alanber4code.alanberlog.AlanberLogTestActivity;
import com.itssc.alanber4code.base.activity.BaseCommonActivity;
import com.itssc.alanber4code.base.presenter.BaseCommonPresenter;

import alanberlog.AlanberLog;

public class TestActivity extends BaseCommonActivity implements View.OnClickListener {

    private Button btn_popupwindow;
    private Button btn_dialog;
    private Button btn_alanberlog;
    private Button btn_alanberlog_flush;

    @Override
    protected BaseCommonPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initSomething() {
        btn_popupwindow = findViewById(R.id.btn_popupwindow);
        btn_dialog = findViewById(R.id.btn_dialog);
        btn_alanberlog = findViewById(R.id.btn_alanberlog);
        btn_alanberlog_flush = findViewById(R.id.btn_alanberlog_flush);

        btn_popupwindow.setOnClickListener(this);
        btn_dialog.setOnClickListener(this);
        btn_alanberlog.setOnClickListener(this);
        btn_alanberlog_flush.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_popupwindow) {

        } else if (id == R.id.btn_dialog) {

        } else if (id == R.id.btn_alanberlog) {
            startActivity(new Intent(this, AlanberLogTestActivity.class));
        } else if (id == R.id.btn_alanberlog_flush) {
            AlanberLog.flush();
        }
    }
}

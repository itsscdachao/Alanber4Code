package com.itssc.alanber4code;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.itssc.alanber4code.alanberlog.AlanberLogTestActivity;
import com.itssc.alanber4code.base.activity.BaseCommonActivity;
import com.itssc.alanber4code.base.presenter.BaseCommonPresenter;
import com.itssc.tool_imageloader.imageloader.AlanberImageLoader;
import com.itssc.tool_widget.commonview.DragView.DragView;

import alanberlog.AlanberLog;

public class TestActivity extends BaseCommonActivity implements View.OnClickListener {

    private Button btn_popupwindow;
    private Button btn_dialog;
    private Button btn_alanberlog;
    private Button btn_alanberlog_flush;
    private Button btn_crash_spider;
    private View btn_password_view;
    private DragView dragview;

    @Override
    protected BaseCommonPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test;
    }

    @Override
    protected void init() {
        btn_popupwindow = findViewById(R.id.btn_popupwindow);
        btn_dialog = findViewById(R.id.btn_dialog);
        btn_alanberlog = findViewById(R.id.btn_alanberlog);
        btn_alanberlog_flush = findViewById(R.id.btn_alanberlog_flush);
        btn_crash_spider = findViewById(R.id.btn_crash_spider);
        btn_password_view = findViewById(R.id.btn_password_view);
        dragview = findViewById(R.id.dragview);
        AlanberImageLoader.getInstance().defaultDisplayImage(this, "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg", dragview, 0);

        btn_popupwindow.setOnClickListener(this);
        btn_dialog.setOnClickListener(this);
        btn_alanberlog.setOnClickListener(this);
        btn_alanberlog_flush.setOnClickListener(this);
        btn_crash_spider.setOnClickListener(this);
        btn_password_view.setOnClickListener(this);
        dragview.setOnClickListener(this);
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
        } else if (id == R.id.btn_crash_spider) {
            startActivity(new Intent(this, CrashTestActivity.class));
        } else if (id == R.id.btn_password_view) {
            startActivity(new Intent(this, PasswordViewTestActivity.class));
        } else if (id == R.id.dragview) {
            Toast.makeText(this, "i am a robot", Toast.LENGTH_SHORT).show();
        }
    }
}

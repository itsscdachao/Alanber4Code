package com.itssc.tool_widget.commonview.advance_recycleview.DefaultHeaderAndFooterCreator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.itssc.tool_widget.R;
import com.itssc.tool_widget.commonview.advance_recycleview.PullToRefresh.PullToRefreshRecyclerView;
import com.itssc.tool_widget.commonview.advance_recycleview.PullToRefresh.RefreshHeaderCreator;

public class DefaultRefreshHeaderCreator extends RefreshHeaderCreator {

    private View mRefreshView;
    private ImageView iv;
    private TextView tv;

    private int rotationDuration = 200;

    private int loadingDuration = 1000;
    private ValueAnimator ivAnim;


    @Override
    public boolean onStartPull(float distance, int lastState) {
        if (lastState == PullToRefreshRecyclerView.STATE_DEFAULT) {
            iv.setImageResource(R.mipmap.arrow_down);
            iv.setRotation(0f);
            tv.setText("下拉刷新");
        } else if (lastState == PullToRefreshRecyclerView.STATE_RELEASE_TO_REFRESH) {
            startArrowAnim(0);
            tv.setText("下拉刷新");
        }
        return true;
    }

    @Override
    public void onStopRefresh() {
        if (ivAnim != null) {
            ivAnim.removeAllUpdateListeners();
            ivAnim.cancel();
        }
    }


    @Override
    public boolean onReleaseToRefresh(float distance, int lastState) {
        if (lastState == PullToRefreshRecyclerView.STATE_DEFAULT) {
            iv.setImageResource(R.mipmap.arrow_down);
            iv.setRotation(-180f);
            tv.setText("松开更新");
        } else if (lastState == PullToRefreshRecyclerView.STATE_PULLING) {
            startArrowAnim(-180f);
            tv.setText("松开更新");
        }
        return true;
    }

    @Override
    public void onStartRefreshing() {
        iv.setImageResource(R.mipmap.loading);
        startLoadingAnim();
        tv.setText("更新中...");
    }

    @Override
    public View getRefreshView(Context context, RecyclerView recyclerView) {
        mRefreshView = LayoutInflater.from(context).inflate(R.layout.layout_ptr_ptl, recyclerView, false);
        iv = (ImageView) mRefreshView.findViewById(R.id.iv);
        tv = (TextView) mRefreshView.findViewById(R.id.tv);
        return mRefreshView;
    }

    private void startArrowAnim(float roration) {
        if (ivAnim != null) {
            ivAnim.removeAllUpdateListeners();
            ivAnim.cancel();
        }
        final float fRoration = roration;
        float startRotation = iv.getRotation();
        ivAnim = ObjectAnimator.ofFloat(startRotation, fRoration).setDuration(rotationDuration);
        ivAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                iv.setRotation((Float) animation.getAnimatedValue());
                if (((Float) animation.getAnimatedValue()) == fRoration) {
                    ivAnim.removeAllUpdateListeners();
                    ivAnim.cancel();
                }
            }
        });
        ivAnim.start();
    }

    private void startLoadingAnim() {
        if (ivAnim != null) {
            ivAnim.removeAllUpdateListeners();
            ivAnim.cancel();
        }
        ivAnim = ObjectAnimator.ofFloat(0, 360).setDuration(loadingDuration);
        ivAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                iv.setRotation((Float) animation.getAnimatedValue());
            }
        });
        ivAnim.setRepeatMode(ObjectAnimator.RESTART);
        ivAnim.setRepeatCount(ObjectAnimator.INFINITE);
        ivAnim.setInterpolator(new LinearInterpolator());
        ivAnim.start();
    }
}

package com.itssc.tool_widget.commonview.dialog;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.itssc.tool_widget.R;
import com.itssc.tool_widget.commonview.util.ScreenUtil;

public class CommonDialog extends CommonBaseDialog implements IDialog {
    private static final String FTag = "dialogTag";

    private CommonDialogController controller;

    private OnBuildChildViewListener buildListener;
    private IDialog.OnDismissListener dismissListener;

    public CommonDialog() {
        controller = new CommonDialogController(this);
    }

    @Override
    protected int getLayoutRes() {
        return controller.getLayoutRes();
    }

    @Override
    protected View getDialogView() {
        return controller.getDialogView();
    }

    @Override
    protected int getDialogWidth() {
        return controller.getDialogWidth();
    }

    @Override
    protected int getDialogHeight() {
        return controller.getDialogHeight();
    }

    @Override
    protected boolean isCancelableOutside() {
        return controller.isCancelableOutside();
    }

    @Override
    public boolean isCancelable() {
        return controller.isCancelable();
    }

    @Override
    public float getDimAmount() {
        return controller.getDimAmount();
    }

    @Override
    protected int getGravity() {
        return controller.getGravity();
    }

    @Override
    protected int getAnimRes() {
        return controller.getAnimRes();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //设置默认子View布局
        controller.setChildView(view);
        //回调给调用者，用来设置子View及点击事件等
        if (buildListener != null && getLayoutRes() != 0 && getBaseView() != null) {
            buildListener.onBuildChildView(this, getBaseView(), getLayoutRes());
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (controller != null) {
            controller = null;
        }
    }

    @Override
    public void onDestroy() {
        //监听dialog的dismiss
        if (dismissListener != null) {
            dismissListener.onDismiss(this);
        }
        super.onDestroy();
    }

    public static class Builder {
        private CommonDialogController.CommonParams params;
        private OnBuildChildViewListener buildListener;
        private IDialog.OnDismissListener dismissListener;

        public Builder(Context context) {
            if (!(context instanceof Activity)) {
                throw new IllegalArgumentException("Context must be Activity");
            }
            params = new CommonDialogController.CommonParams();
            params.fragmentManager = ((Activity) context).getFragmentManager();
            params.context = context;
        }

        /**
         * 设置DialogView
         *
         * @param layoutRes 布局文件
         * @return Builder
         */
        public Builder setDialogView(int layoutRes) {
            params.layoutRes = layoutRes;
            return this;
        }

        /**
         * 设置DialogView
         *
         * @param dialogView View
         * @return Builder
         */
        public Builder setDialogView(View dialogView) {
            params.dialogView = dialogView;
            return this;
        }

        /**
         * 设置屏幕宽度百分比
         *
         * @param percentage 0.0f~1.0f
         * @return Builder
         */
        public Builder setScreenWidthPercent(float percentage) {
            params.dialogWidth = (int) (ScreenUtil.getScreenWidth((Activity) params.context) * percentage);
            return this;
        }

        /**
         * 设置屏幕高度百分比
         *
         * @param percentage 0.0f~1.0f
         * @return Builder
         */
        public Builder setScreenHeightPercent(float percentage) {
            params.dialogHeight = (int) (ScreenUtil.getScreenHeight((Activity) params.context) * percentage);
            return this;
        }

        /**
         * 监听dialog的dismiss
         *
         * @param dismissListener IDialog.OnDismissListener
         * @return Builder
         */
        public Builder setOnDismissListener(IDialog.OnDismissListener dismissListener) {
            this.dismissListener = dismissListener;
            return this;
        }

        /**
         * 设置Dialog的宽度
         *
         * @param width 宽度
         * @return Builder
         */
        public Builder setWidth(int width) {
            params.dialogWidth = width;
            return this;
        }

        /**
         * 设置Dialog的高度
         *
         * @param height 高度
         * @return Builder
         */
        public Builder setHeight(int height) {
            params.dialogHeight = height;
            return this;
        }

        /**
         * 设置背景色色值
         *
         * @param percentage 0.0f~1.0f 1.0f为完全不透明
         * @return Builder
         */
        public Builder setWindowBackgroundPercent(float percentage) {
            params.dimAmount = percentage;
            return this;
        }

        /**
         * 设置Gravity
         *
         * @param gravity Gravity
         * @return Builder
         */
        public Builder setGravity(int gravity) {
            params.gravity = gravity;
            return this;
        }

        /**
         * 设置dialog外点击是否可以让dialog消失
         *
         * @param cancelableOutSide true 则在dialog屏幕外点击可以使dialog消失
         * @return Builder
         */
        public Builder setCancelableOutSide(boolean cancelableOutSide) {
            params.isCancelableOutside = cancelableOutSide;
            return this;
        }

        /**
         * 设置是否屏蔽物理返回键
         *
         * @param cancelable true 点击物理返回键可以让dialog消失；反之不消失
         * @return Builder
         */
        public Builder setCancelable(boolean cancelable) {
            params.cancelable = cancelable;
            return this;
        }

        /**
         * 构建子View的listener
         *
         * @param listener IDialog.OnBuildChildViewListener
         * @return Builder
         */
        public Builder setBuildChildListener(OnBuildChildViewListener listener) {
            this.buildListener = listener;
            return this;
        }

        /**
         * 设置dialog的动画效果
         *
         * @param animStyle 动画资源文件
         * @return Builder
         */
        public Builder setAnimStyle(int animStyle) {
            params.animRes = animStyle;
            return this;
        }


        /**
         * 设置默认右侧点击按钮
         *
         * @param onclickListener IDialog.OnClickListener
         * @return Builder
         */
        public Builder setPositiveButton(IDialog.OnClickListener onclickListener) {
            return setPositiveButton("确定", onclickListener);
        }

        /**
         * 设置默认右侧点击按钮及文字
         *
         * @param btnStr          右侧文字
         * @param onclickListener IDialog.OnClickListener
         * @return Builder
         */
        public Builder setPositiveButton(String btnStr, IDialog.OnClickListener onclickListener) {
            params.positiveBtnListener = onclickListener;
            params.positiveStr = btnStr;
            params.showBtnRight = true;
            return this;
        }

        /**
         * 设置左侧按钮
         *
         * @param onclickListener IDialog.OnClickListener
         * @return Builder
         */
        public Builder setNegativeButton(IDialog.OnClickListener onclickListener) {
            return setNegativeButton("取消", onclickListener);
        }

        /**
         * 设置左侧文字及按钮
         *
         * @param btnStr          文字
         * @param onclickListener IDialog.OnClickListener
         * @return Builder
         */
        public Builder setNegativeButton(String btnStr, IDialog.OnClickListener onclickListener) {
            params.negativeBtnListener = onclickListener;
            params.negativeStr = btnStr;
            params.showBtnLeft = true;
            return this;
        }

        /**
         * 设置默认dialog的title
         *
         * @param title 标题
         * @return Builder
         */
        public Builder setTitle(String title) {
            params.titleStr = title;
            return this;
        }

        /**
         * 设置默认dialog的内容
         *
         * @param content 内容
         * @return Builder
         */
        public Builder setContent(String content) {
            params.contentStr = content;
            return this;
        }

        private CommonDialog create() {
            CommonDialog dialog = new CommonDialog();
            params.apply(dialog.controller);
            dialog.buildListener = buildListener;
            dialog.dismissListener = dismissListener;
            return dialog;
        }

        /**
         * 展示Dialog
         *
         * @return CommonDialog
         */
        public CommonDialog show() {
            if (params.layoutRes <= 0 && params.dialogView == null) {
                //如果没有设置布局 提供默认设置
                setDefaultOption();
            }
            CommonDialog dialog = create();
            removePreDialog();
            dialog.show(params.fragmentManager, FTag);
            return dialog;
        }

        /**
         * 设置默认Dialog的配置
         */
        private void setDefaultOption() {
            params.cancelable = false;
            params.isCancelableOutside = false;
            params.gravity = Gravity.CENTER;
            params.layoutRes = R.layout.layout_dialog_common;
            params.dimAmount = 0.5f;
            params.dialogWidth = (int) (ScreenUtil.getScreenWidth((Activity) params.context) * 0.85f);
            params.dialogHeight = WindowManager.LayoutParams.WRAP_CONTENT;
        }

        /**
         * 移除之前的dialog
         */
        private void removePreDialog() {
            FragmentTransaction ft = params.fragmentManager.beginTransaction();
            Fragment prev = params.fragmentManager.findFragmentByTag(FTag);
            if (prev != null) {
                ft.remove(prev);
            }
            ft.commitAllowingStateLoss();
        }


    }


}

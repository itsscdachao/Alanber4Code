package com.itssc.tool_widget.commonview.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.itssc.tool_widget.R;

/**
 * author：dachao on 2018/12/14 10:25
 */

@Deprecated
public class CommonAlertDialog extends Dialog {

    public CommonAlertDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Button mLeftButton;
        private Button mRightButton;
        private View mLayout;
        private ImageView mIcon;
        private TextView mTitle;
        private TextView mMessage;
        private Button mButton;
        private View.OnClickListener mButtonClickListener;
        private View.OnClickListener mLeftButtonClickListener;
        private View.OnClickListener mRightButtonClickListener;
        private CommonAlertDialog mDialog;

        public Builder(Context context) {
            mDialog = new CommonAlertDialog(context, R.style.translate_style);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //加载布局文件
            mLayout = inflater.inflate(R.layout.layout_alert_dialog, null, false);
            //添加布局文件到 Dialog
            mDialog.addContentView(mLayout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            mTitle = mLayout.findViewById(R.id.tv_title);
            mMessage = mLayout.findViewById(R.id.tv_message);
            mLeftButton = mLayout.findViewById(R.id.btn_left);
            mRightButton = mLayout.findViewById(R.id.btn_right);
        }

        /**
         * 通过 ID 设置 Dialog 图标
         */
        public Builder setIcon(int resId) {
            mIcon.setImageResource(resId);
            return this;
        }

        /**
         * 用 Bitmap 作为 Dialog 图标
         */
        public Builder setIcon(Bitmap bitmap) {
            mIcon.setImageBitmap(bitmap);
            return this;
        }

        /**
         * 设置 Dialog 标题
         */
        public Builder setTitle(String title) {
            mTitle.setText(title);
            mTitle.setVisibility(View.VISIBLE);
            return this;
        }

        /**
         * 设置 Message
         */
        public Builder setMessage(String message) {
            mMessage.setText(message);
            return this;
        }

        /**
         * 设置左边按钮文字和监听
         */
        public Builder setLeftButtonListener(String text, View.OnClickListener listener) {
            mLeftButton.setText(text);
            mLeftButtonClickListener = listener;
            return this;
        }

        /**
         * 设置右边按钮文字和监听
         */
        public Builder setRightButtonListener(String text, View.OnClickListener listener) {
            mLeftButton.setText(text);
            mRightButtonClickListener = listener;
            return this;
        }

        public CommonAlertDialog create() {
            mLeftButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    mLeftButtonClickListener.onClick(v);
                }
            });

            mRightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDialog.dismiss();
                    mRightButtonClickListener.onClick(v);
                }
            });

            mDialog.setContentView(mLayout);
            mDialog.setCancelable(true);                //用户可以点击后退键关闭 Dialog
            mDialog.setCanceledOnTouchOutside(false);   //用户不可以点击外部来关闭 Dialog
            return mDialog;
        }
    }


    /**
     * 展示原生AlertDialog
     *
     * @param activity
     */
    public static void showAlertDialog(Activity activity) {
        final AlertDialog build = new AlertDialog.Builder(activity).create();
        //加载自定义布局
        View view = activity.getLayoutInflater().inflate(R.layout.layout_alert_dialog, null);
        //把自定义的布局设置到dialog中，注意，布局设置一定要在show之前。从第二个参数分别填充内容与边框之间左、上、右、下、的像素
        build.setView(view, 0, 0, 0, 0);
        //一定要先show出来再设置dialog的参数，不然就不会改变dialog的大小了
        build.show();
        //得到当前显示设备的宽度，单位是像素
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        //得到这个dialog界面的参数对象
        WindowManager.LayoutParams params = build.getWindow().getAttributes();
        //设置dialog的界面宽度
        params.width = width - (width / 6);
        //设置dialog高度为包裹内容
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        //设置dialog的位置权重
        params.gravity = Gravity.CENTER;
        //dialog.getWindow().setLayout(width-(width/6), LayoutParams.WRAP_CONTENT);
        //用这个方法设置dialog大小也可以，但是这个方法不能设置重心之类的参数，推荐用Attributes设置
        //最后把这个参数对象设置进去，即与dialog绑定
        build.getWindow().setAttributes(params);
        Button leftButton = view.findViewById(R.id.btn_left);
        Button rightButton = view.findViewById(R.id.btn_right);
        TextView warnMessage = view.findViewById(R.id.tv_message);
        warnMessage.setText("哈哈哈");
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.dismiss();
            }
        });
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                build.dismiss();
            }
        });
    }
}

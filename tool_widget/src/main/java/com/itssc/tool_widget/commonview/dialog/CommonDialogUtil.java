package com.itssc.tool_widget.commonview.dialog;

import android.content.Context;
import android.text.TextUtils;

import com.itssc.tool_widget.R;

import java.util.HashMap;

public class CommonDialogUtil {

    private static HashMap<String, CommonDialog> hashMap = new HashMap<>();

    /**
     * @param context               Context
     * @param title                 标题
     * @param content               内容
     * @param btn1Str               button文字
     * @param positiveClickListener 点击事件
     */
    public static void createDefaultDialog(Context context, String title, String content, String btn1Str, IDialog.OnClickListener positiveClickListener) {
        createDefaultDialog(context, title, content, btn1Str, positiveClickListener, "", null);
    }

    /**
     * @param context               Context
     * @param title                 标题
     * @param content               内容
     * @param btn1Str               左边按钮
     * @param negativeClickListener 左边点击事件
     * @param btn2Str               右边按钮
     * @param positiveClickListener 右边点击事件
     */
    public static void createDefaultDialog(Context context, String title, String content, String btn1Str, IDialog.OnClickListener positiveClickListener, String btn2Str, IDialog.OnClickListener negativeClickListener) {
        CommonDialog.Builder builder = new CommonDialog.Builder(context);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(content)) {
            builder.setContent(content);
        }
        if (positiveClickListener != null) {
            if (TextUtils.isEmpty(btn1Str)) {
                builder.setPositiveButton(positiveClickListener);
            } else {
                builder.setPositiveButton(btn1Str, positiveClickListener);
            }
        }
        if (negativeClickListener != null) {
            if (TextUtils.isEmpty(btn2Str)) {
                builder.setNegativeButton(negativeClickListener);
            } else {
                builder.setNegativeButton(btn2Str, negativeClickListener);
            }
        }
        builder.show();
    }

    /**
     * 创建Loading dialog
     *
     * @param context Context
     */
    public static void createLoadingDialog(Context context) {
        closeLoadingDialog(context);
        CommonDialog.Builder builder = new CommonDialog.Builder(context);
        CommonDialog dialog = builder.setDialogView(R.layout.layout_loading_dialog)
                .setWindowBackgroundPercent(0.5f)
                .setCancelableOutSide(false)
                .setCancelable(false)
                .show();
        hashMap.put(context.getClass().getSimpleName(), dialog);

    }

    /**
     * 关闭loading dialog
     *
     * @param context Context
     */
    public static void closeLoadingDialog(Context context) {
        String dialogKey = context.getClass().getSimpleName();
        CommonDialog dialog = hashMap.get(dialogKey);
        if (dialog != null) {
            hashMap.remove(dialogKey);
            dialog.dismiss();
        }
    }
}

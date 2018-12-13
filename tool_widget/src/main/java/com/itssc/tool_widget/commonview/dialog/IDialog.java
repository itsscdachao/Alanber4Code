package com.itssc.tool_widget.commonview.dialog;

import android.view.View;

/**
 * dialog接口，封装最基础的功能
 */
public interface IDialog {

    void dismiss();

    interface OnBuildChildViewListener {
        void onBuildChildView(IDialog dialog, View view, int layoutRes);
    }

    interface OnClickListener {
        void onClick(IDialog dialog);
    }

    interface OnDismissListener {
        void onDismiss(IDialog dialog);
    }
}

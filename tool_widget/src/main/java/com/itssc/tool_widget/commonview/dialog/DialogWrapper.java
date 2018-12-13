package com.itssc.tool_widget.commonview.dialog;
/**
 * 管理多个dialog 按照dialog的优先级依次弹出
 */

public class DialogWrapper {

    private CommonDialog.Builder dialog;//统一管理dialog的弹出顺序

    public DialogWrapper(CommonDialog.Builder dialog) {
        this.dialog = dialog;
    }

    public CommonDialog.Builder getDialog() {
        return dialog;
    }

    public void setDialog(CommonDialog.Builder dialog) {
        this.dialog = dialog;
    }
}

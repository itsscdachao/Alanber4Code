package com.itssc.alanber4code;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.itssc.alanber4code.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    private CommonDialog build;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        findViewById(R.id.btn_click).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }
        });
    }

    private void dialog(){
        build = new CommonDialog.Builder(this)
                .view(R.layout.layout_common_dialog)//设置弹窗的样式layout
                .style(R.style.AppTheme) //设置主题，这里可以将背景设为透明，这样只显示你需要显示的dialog部分
                .cancelTouchout(true) //设置点击dialog之外是否弹窗消失，true为消失，false为不消失
                .setTitle(R.id.txt_title, "这是一个弹窗标题")//根据id来设置标题的显示文字
                .setMessage(R.id.txt_message, "这是一个弹窗消息内容")//根据id来设置消息内容的显示文字
                .addViewOnclick(R.id.txt_sure, new View.OnClickListener() {//处理确认点击事件
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(HomeActivity.this, "点击了确定按钮", Toast.LENGTH_SHORT).show();
                        build.dismiss();
                    }
                })
                .addViewOnclick(R.id.txt_cancel, new View.OnClickListener() {//处理取消的点击事件
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(HomeActivity.this, "点击了取消按钮", Toast.LENGTH_SHORT).show();
                        build.dismiss();
                    }
                }).build();

        build.show();

    }
}

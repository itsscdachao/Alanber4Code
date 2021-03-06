package com.itssc.alanber4code;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.itssc.alanber4code.alanberlog.AlanberLogTestActivity;
import com.itssc.alanber4code.base.activity.BaseCommonActivity;
import com.itssc.alanber4code.base.presenter.BaseCommonPresenter;
import com.itssc.tool_widget.commonview.dialog.CommonDialog;
import com.itssc.tool_widget.commonview.dialog.CommonDialogManager;
import com.itssc.tool_widget.commonview.dialog.CommonDialogUtil;
import com.itssc.tool_widget.commonview.dialog.DialogWrapper;
import com.itssc.tool_widget.commonview.dialog.IDialog;

public class AlanberDialogTestActivity extends BaseCommonActivity {

    private CommonDialog dialog;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);

//        CommonAlertDialog.showAlertDialog(this);

//        CommonAlertDialog infoDialog = new CommonAlertDialog.Builder(this)
//                .setTitle("Done")
//                .setMessage("Something done")
//                .setLeftButtonListener("NOT OK", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                })
//                .setRightButtonListener("OK", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                })
//                .create();
//        infoDialog.show();
//    }

    @Override
    protected BaseCommonPresenter createPresenter() {
        return null;
    }

    @Override
    protected void initSomething() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    /**
     * 1、默认Dialog:一个Button
     *
     * @param view View
     */
    public void showDefaultDialog(View view) {
        CommonDialogUtil.createDefaultDialog(this, "我是标题", "你好,我们将在30分钟处理，稍后通知您订单结果！",
                "", new IDialog.OnClickListener() {
                    @Override
                    public void onClick(IDialog dialog) {
                        dialog.dismiss();
                    }
                });
    }

    /**
     * 2、默认Dialog:二个Button
     *
     * @param view View
     */
    public void showDefaultDialogTwo(View view) {
        CommonDialogUtil.createDefaultDialog(this, "分享", "分享此接单码，您和乘客都将获得现金红包！",
                "立即分享", new IDialog.OnClickListener() {
                    @Override
                    public void onClick(IDialog dialog) {
                        Toast.makeText(AlanberDialogTestActivity.this, "立即分享", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                },
                "有钱不要", new IDialog.OnClickListener() {
                    @Override
                    public void onClick(IDialog dialog) {
                        Toast.makeText(AlanberDialogTestActivity.this, "有钱不要", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
    }


    /**
     * 3、自定义Dialog 基本使用
     *
     * @param view View
     */
    public void showBaseUseDialog(View view) {
        new CommonDialog.Builder(this)
                .setDialogView(R.layout.layout_dialog)//设置dialog布局
                .setAnimStyle(R.style.translate_style)//设置动画 默认没有动画
                .setScreenWidthPercent(0.85f) //设置屏幕宽度比例 0.0f-1.0f
                .setGravity(Gravity.CENTER)//设置Gravity
                .setWindowBackgroundPercent(0.2f)//设置背景透明度 0.0f-1.0f 1.0f完全不透明
                .setCancelable(true)//设置是否屏蔽物理返回键 true不屏蔽  false屏蔽
                .setCancelableOutSide(true)//设置dialog外点击是否可以让dialog消失
                .setOnDismissListener(new IDialog.OnDismissListener() {
                    @Override
                    public void onDismiss(IDialog dialog) {
                        //监听dialog dismiss的回调
                        Toast.makeText(AlanberDialogTestActivity.this, "dismiss回调", Toast.LENGTH_SHORT).show();
                    }
                })
                .setBuildChildListener(new IDialog.OnBuildChildViewListener() {
                    //设置子View
                    @Override
                    public void onBuildChildView(final IDialog dialog, View view, int layoutRes) {
                        //dialog: IDialog
                        //view： DialogView
                        //layoutRes :Dialog的资源文件 如果一个Activity里有多个dialog 可以通过layoutRes来区分
                        final EditText editText = view.findViewById(R.id.et_content);
                        Button btn_ok = view.findViewById(R.id.btn_ok);
                        btn_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String editTextStr = null;
                                if (!TextUtils.isEmpty(editText.getText())) {
                                    editTextStr = editText.getText().toString();
                                }
                                dialog.dismiss();
                                Toast.makeText(AlanberDialogTestActivity.this, editTextStr, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).show();
    }

    /**
     * 4、展示进度条
     *
     * @param view View
     */

    public void showLoadingDialog(View view) {
        CommonDialogUtil.createLoadingDialog(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                CommonDialogUtil.closeLoadingDialog(AlanberDialogTestActivity.this);
            }
        }, 5000);
    }

    /**
     * 5、全屏广告弹窗
     *
     * @param view View
     */
    public void showAdDialog(View view) {
        new CommonDialog.Builder(this)
                .setDialogView(R.layout.layout_ad_dialog)
                .setWindowBackgroundPercent(0.5f)
                .setBuildChildListener(new IDialog.OnBuildChildViewListener() {
                    @Override
                    public void onBuildChildView(final IDialog dialog, View view, int layoutRes) {
                        ImageView iv_close = view.findViewById(R.id.iv_close);
                        iv_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        ImageView iv_ad = view.findViewById(R.id.iv_ad);
                        iv_ad.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(AlanberDialogTestActivity.this, "点击广告", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                    }
                }).show();
    }

    /**
     * 6、底部选择弹窗
     *
     * @param view View
     */
    public void showBottomDialog(View view) {
        new CommonDialog.Builder(this)
                .setDialogView(R.layout.layout_bottom_up)
                .setWindowBackgroundPercent(0.5f)
                .setAnimStyle(R.style.AnimUp)
                .setCancelableOutSide(true)
                .setCancelableOutSide(true)
                .setBuildChildListener(new IDialog.OnBuildChildViewListener() {
                    @Override
                    public void onBuildChildView(final IDialog dialog, View view, int layoutRes) {
                        Button btn_take_photo = view.findViewById(R.id.btn_take_photo);
                        btn_take_photo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(AlanberDialogTestActivity.this, "拍照", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

                        Button btn_select_photo = view.findViewById(R.id.btn_select_photo);
                        btn_select_photo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(AlanberDialogTestActivity.this, "相册选取", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

                        Button btn_cancel_dialog = view.findViewById(R.id.btn_cancel_dialog);
                        btn_cancel_dialog.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(AlanberDialogTestActivity.this, "取消", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setScreenWidthPercent(1.0f)
                .setGravity(Gravity.BOTTOM).show();
    }

    /**
     * 8、全局管理dialog
     *
     * @param view View
     */
    public void showGlobalDialog(View view) {
        //Build第一个Dialog
        CommonDialog.Builder builder1 = new CommonDialog.Builder(this)
                .setDialogView(R.layout.layout_ad_dialog)
                .setWindowBackgroundPercent(0.5f)
                .setBuildChildListener(new IDialog.OnBuildChildViewListener() {
                    @Override
                    public void onBuildChildView(final IDialog dialog, View view, int layoutRes) {
                        ImageView iv_close = view.findViewById(R.id.iv_close);
                        iv_close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        ImageView iv_ad = view.findViewById(R.id.iv_ad);
                        iv_ad.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(AlanberDialogTestActivity.this, "点击广告", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                    }
                });
        //Build第二个Dialog
        CommonDialog.Builder builder2 = new CommonDialog.Builder(this)
                .setDialogView(R.layout.layout_bottom_up)
                .setWindowBackgroundPercent(0.5f)
                .setAnimStyle(R.style.AnimUp)
                .setBuildChildListener(new IDialog.OnBuildChildViewListener() {
                    @Override
                    public void onBuildChildView(final IDialog dialog, View view, int layoutRes) {
                        Button btn_take_photo = view.findViewById(R.id.btn_take_photo);
                        btn_take_photo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(AlanberDialogTestActivity.this, "拍照", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

                        Button btn_select_photo = view.findViewById(R.id.btn_select_photo);
                        btn_select_photo.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(AlanberDialogTestActivity.this, "相册选取", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });

                        Button btn_cancel_dialog = view.findViewById(R.id.btn_cancel_dialog);
                        btn_cancel_dialog.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(AlanberDialogTestActivity.this, "取消", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                    }
                })
                .setScreenWidthPercent(1.0f)
                .setGravity(Gravity.BOTTOM);
        //添加第一个Dialog
        CommonDialogManager.getInstance().requestShow(new DialogWrapper(builder1));
        //添加第二个Dialog
        CommonDialogManager.getInstance().requestShow(new DialogWrapper(builder2));
    }

    /**
     * 7、分享dialog
     *
     * @param view View
     */
    public void showDialogShare(View view) {
        dialog = new CommonDialog.Builder(this)
                .setDialogView(R.layout.layout_share)
                .setWindowBackgroundPercent(0.5f)
                .setScreenWidthPercent(1.0f)
                .setGravity(Gravity.BOTTOM)
                .setCancelable(false)
                .setCancelableOutSide(false)
                .setAnimStyle(R.style.AnimUp)
                .setBuildChildListener(new IDialog.OnBuildChildViewListener() {
                    @Override
                    public void onBuildChildView(final IDialog dialog, View view, int layoutRes) {
                        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
                        recyclerView.setLayoutManager(new LinearLayoutManager(AlanberDialogTestActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        recyclerView.setAdapter(new ShareAdapter());

                        Button btn_cancel_dialog = view.findViewById(R.id.btn_cancel_dialog);
                        btn_cancel_dialog.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(AlanberDialogTestActivity.this, "取消", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        });
                    }
                }).show();
    }

    public void testLog() {
        startActivity(new Intent(this, AlanberLogTestActivity.class));
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

    class ShareAdapter extends RecyclerView.Adapter<ShareAdapter.ShareHolder> {

        @Override
        public ShareHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_share, parent, false);
            return new ShareHolder(view);
        }

        @Override
        public void onBindViewHolder(ShareHolder holder, int position) {
            holder.ll_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(AlanberDialogTestActivity.this, "分享", Toast.LENGTH_SHORT).show();
                    dismissDialog();
                }
            });
        }

        @Override
        public int getItemCount() {
            return 8;
        }

        class ShareHolder extends RecyclerView.ViewHolder {
            LinearLayout ll_share;

            public ShareHolder(View itemView) {
                super(itemView);
                ll_share = itemView.findViewById(R.id.ll_share);
            }
        }
    }


    /**
     * 关闭弹窗 注意dialog=null;防止内存泄漏
     */
    private void dismissDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}

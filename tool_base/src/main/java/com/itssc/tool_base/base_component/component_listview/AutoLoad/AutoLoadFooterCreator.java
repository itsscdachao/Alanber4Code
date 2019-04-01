package com.itssc.tool_base.base_component.component_listview.AutoLoad;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class AutoLoadFooterCreator  {
    /***
     * 获得footer
     */
    protected abstract View getLoadView(Context context, RecyclerView recyclerView);
    /***
     * 没有更多
     */
    protected abstract View getNoMoreView(Context context, RecyclerView recyclerView);
}

package com.itssc.tool_base.base_component.component_list.SimpleAdapter;

import android.content.Context;
import android.view.ViewGroup;
import java.util.ArrayList;

public abstract class SimpleTypeAdapter<T> extends MultiTypeAdapter {
    protected int mLayoutId;

    public SimpleTypeAdapter(Context context, ArrayList<T> datas, int layoutId) {
        super(context, datas);
        this.mLayoutId = layoutId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected int getLayoutIdByType(int viewType) {
        return mLayoutId;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    protected void onBindViewHolder(ViewHolder holder, int type, Object data) {
        onBindViewHolder(holder, (T) data);
    }

    protected abstract void onBindViewHolder(ViewHolder holder, T data);
}

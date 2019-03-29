package com.itssc.tool_base.base_component.component_list.SimpleAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiTypeAdapter extends RecyclerView.Adapter<ViewHolder> {

    protected Context mContext;
    protected List mDatas;

    public MultiTypeAdapter(Context mContext, ArrayList mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = getLayoutIdByType(viewType);
        return ViewHolder.get(mContext, parent, layoutId);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        onBindViewHolder(holder, getItemViewType(position), mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    protected abstract int getLayoutIdByType(int viewType);

    @Override
    public abstract int getItemViewType(int position);

    protected abstract void onBindViewHolder(ViewHolder holder, int type, Object data);
}

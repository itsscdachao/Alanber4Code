package com.itssc.tool_widget.commonview.recycleview.LayoutManager;

import android.content.Context;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

public class PTLLinearLayoutManager extends StaggeredGridLayoutManager {
    public PTLLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setGapStrategy(GAP_HANDLING_NONE);
    }

    private PTLLinearLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    public PTLLinearLayoutManager() {
        super(1,VERTICAL);
        setGapStrategy(GAP_HANDLING_NONE);
    }

    public PTLLinearLayoutManager(int orientation) {
        super(1,orientation);
        setGapStrategy(GAP_HANDLING_NONE);
    }

    public PTLLinearLayoutManager(int orientation, boolean reverseLayout) {
        super(1,orientation);
        setReverseLayout(reverseLayout);
        setGapStrategy(GAP_HANDLING_NONE );
    }
}

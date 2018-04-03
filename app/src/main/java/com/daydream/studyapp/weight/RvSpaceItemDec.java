package com.daydream.studyapp.weight;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * recycler view 间距
 *
 * @author gjc
 * @version ;;
 * @since 2018-04-03
 */

public class RvSpaceItemDec extends RecyclerView.ItemDecoration {

    private final int mSpace;

    public RvSpaceItemDec(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        if (position % 2 == 0) {
            outRect.left = mSpace;
            outRect.right = mSpace / 2;
            outRect.bottom = mSpace;
        } else {
            outRect.left = mSpace / 2;
            outRect.right = mSpace;
            outRect.bottom = mSpace;
        }

        if (position == 0 || position == 1) {
            outRect.top = mSpace;
        }
    }
}

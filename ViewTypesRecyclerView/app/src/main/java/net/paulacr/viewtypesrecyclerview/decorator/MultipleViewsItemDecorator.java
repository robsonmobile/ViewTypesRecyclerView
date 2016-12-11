package net.paulacr.viewtypesrecyclerview.decorator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import net.paulacr.viewtypesrecyclerview.R;

/**
 * Created by paularosa on 11/12/16.
 */

public class MultipleViewsItemDecorator extends RecyclerView.ItemDecoration {

    private Drawable divider;

    public MultipleViewsItemDecorator(Context context) {
        divider = context.getResources().getDrawable(R.drawable.drawable_line);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int dividerLeft = parent.getPaddingLeft();
        int dividerRight = parent.getPaddingRight();

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount -1; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int dividerTop = child.getBottom() + params.bottomMargin;
            int dividerBottom = dividerTop + divider.getIntrinsicHeight();

            divider.setBounds(dividerLeft, dividerTop, dividerRight, dividerBottom);
            divider.draw(c);
        }

    }
}

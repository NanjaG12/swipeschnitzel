package com.swipeschnitzel.app;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by Nanja on 9.05.2014.
 */
public class DrawView extends View {

    Paint paint = new Paint();

    public DrawView(Context context) {
        super(context);
        paint.setColor(getResources().getColor(R.color.redline));
    }
    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawLine(0, 0, 20, 20, paint);
        canvas.drawLine(20, 0, 0, 20, paint);
    }
}

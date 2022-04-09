package com.oneparcent.practical7;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

public class Ball extends View {
    public String colorHex = "#ff0000";
    private final Paint drawPaint;
    private float size;
    public float radius = 50;
    public Ball(final Context context, final AttributeSet attr){
        super(context, attr);
        drawPaint = new Paint();
        drawPaint.setAntiAlias(true);
        setOnMeasureCallback();
    }
    @Override
    protected void onDraw(final Canvas canvas){
        super.onDraw(canvas);
        drawPaint.setColor(Color.parseColor(colorHex));
        canvas.drawCircle(size, size, radius, drawPaint);
    }
    private void setOnMeasureCallback(){
        ViewTreeObserver vto = getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                removeOnGlobalLayoutListener(this);
                size = getMeasuredWidth() / 2;
            }
        });
    }
    @TargetApi(Build.VERSION_CODES.P)
    private void removeOnGlobalLayoutListener(ViewTreeObserver.OnGlobalLayoutListener listener){
        getViewTreeObserver().removeOnGlobalLayoutListener(listener);
    }
}

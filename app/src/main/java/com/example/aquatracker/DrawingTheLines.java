package com.example.aquatracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class DrawingTheLines extends View {

    private static final String TAG = "DrawingTheLines";

    Integer progress=0;



    public DrawingTheLines(Context context, int pro) {
        super(context);
        progress=pro;
        Log.d(TAG, "DrawingTheBall: "+ pro);
    }

    public void setProgress(int value){
        progress=value;
    }

    public DrawingTheLines(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawingTheLines(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawingTheLines(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);



        //rect.set(getWidth(),progress,10,10);


        Paint black = new Paint();
        black.setColor(Color.rgb(0,0,0));
        black.setStyle(Paint.Style.FILL);
        black.setStrokeWidth(3f);

        canvas.drawLine(1,1,1,progress,black);

        //canvas.drawText("Water tank Level: "+ temp,canvas.getWidth()/2,canvas.getHeight()/2,black);
        //invalidate();

    }
}
package com.example.aquatracker;

import android.app.Application;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import static android.widget.Toast.*;

public class DrawingTheBall extends View {

    private static final String TAG = "DrawingTheBall";

    Integer progress=0;

    Rect rect= new Rect();
    Paint paint= new Paint();


    public DrawingTheBall(Context context,int pro) {
        super(context);
        progress=pro;
        Log.d(TAG, "DrawingTheBall: "+ pro);
    }

    public void setProgress(int value){
        progress=value;
    }

    public DrawingTheBall(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawingTheBall(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawingTheBall(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int temp=progress;

        progress=((getHeight()*progress)/100);
        progress=getHeight()-progress;

        rect.set(getWidth()-6,getHeight()-6,15,progress);


        //rect.set(getWidth(),progress,10,10);

        Paint blue= new Paint();
        blue.setColor(Color.rgb(63,80,165));
        blue.setStyle(Paint.Style.FILL);

        Paint black = new Paint();
        black.setColor(Color.rgb(0,0,0));
        black.setStyle(Paint.Style.FILL_AND_STROKE);
        black.setStrokeWidth(6f);


        Paint black1 = new Paint();
        black1.setColor(Color.rgb(0,0,0));
        black1.setStyle(Paint.Style.FILL_AND_STROKE);
        black1.setStrokeWidth(6f);


        canvas.drawLine(10,10,10,getHeight(),black);
        black.setStrokeWidth(6f);
        canvas.drawLine(10,getHeight(),getWidth(),getHeight(),black1);
        black.setStrokeWidth(6f);
        canvas.drawLine(getWidth(),10,getWidth(),getHeight(),black);

        canvas.drawRect(rect,blue);

        //canvas.drawText("Water tank Level: "+ temp,canvas.getWidth()/2,canvas.getHeight()/2,black);
        //invalidate();

    }
}
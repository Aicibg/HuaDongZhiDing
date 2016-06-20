package com.loadview.dellidc.myloadview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Dellidc on 2016/6/14.
 */
public class MyloadView extends View {
    private float lineOneStartY,lineOneEndY;
    private float lineSecondStartY,lineSecondEndY;
    private float lineThirdStartY,lineThirdEndY;
    private boolean onPlus;
    private Paint mpaint;

    public MyloadView(Context context) {
        this(context,null);
    }

    public MyloadView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyloadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        mpaint=new Paint();
        mpaint.setColor(Color.BLACK);
        mpaint.setStrokeWidth(20);
        mpaint.setStyle(Paint.Style.FILL);

        lineOneStartY=0;
        lineOneEndY=100;
        lineSecondStartY=10;
        lineSecondEndY=80;
        lineThirdStartY=0;
        lineThirdEndY=100;
        onPlus=true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(80,lineOneStartY,80,lineOneEndY,mpaint);
        canvas.drawLine(150,lineSecondStartY,150,lineSecondEndY,mpaint);
        canvas.drawLine(220,lineThirdStartY,220,lineThirdEndY,mpaint);

        if (onPlus){
            lineOneStartY++;
            lineThirdStartY++;
            lineSecondStartY--;
            lineOneEndY--;
            lineSecondEndY++;
            lineThirdEndY--;
        }else{
            lineOneStartY--;
            lineThirdStartY--;
            lineSecondStartY++;
            lineOneEndY++;
            lineSecondEndY--;
            lineThirdEndY++;
        }
        if(lineOneStartY==25){
            onPlus=false;
        }else if(lineOneStartY==0){
            onPlus=true;
        }
        //调用ondraw()
        invalidate();
    }
}

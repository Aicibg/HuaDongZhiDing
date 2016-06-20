package com.loadview.dellidc.myloadview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by Dellidc on 2016/6/17.
 */
public class HoveringScrollView extends ScrollView {

 private OnScrollListening onScrollListening;
    private int lastScrolly;

    public void setOnScrollListening(OnScrollListening onScrollListening){
        this.onScrollListening=onScrollListening;
    }
    public HoveringScrollView(Context context) {
       this(context,null);
    }

    public HoveringScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HoveringScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int scrolly=HoveringScrollView.this.getScrollY();
            if(lastScrolly!=scrolly){
                lastScrolly=scrolly;
                handler.sendMessageDelayed(handler.obtainMessage(),10);
            }
            if(onScrollListening!=null){
                onScrollListening.onScroll(scrolly);
            }
        }
    };

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        if(onScrollListening!=null){
//            onScrollListening.onScroll(lastScrolly=this.getScrollY());
//        }
//        switch (ev.getAction()){
//            case MotionEvent.ACTION_UP:
//                handler.sendMessageDelayed(handler.obtainMessage(),10);
//                break;
//        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(onScrollListening!=null){
            onScrollListening.onScroll(t);
        }
    }

    public interface OnScrollListening{
        public void onScroll(int scrolly);
    }
}

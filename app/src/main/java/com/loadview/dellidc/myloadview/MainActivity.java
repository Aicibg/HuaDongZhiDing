package com.loadview.dellidc.myloadview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;

public class MainActivity extends AppCompatActivity implements ObservableScrollViewCallbacks{
   private ObservableScrollView mScrollView;
    private ImageView ivAdd,ivImage,ivHeader;
    private Toolbar mtoolBar;
    private int imageviewHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();


    }

    private void initViews() {
        mScrollView= (ObservableScrollView) findViewById(R.id.os_scrollview);
        mScrollView.setScrollViewCallbacks(this);
        ivAdd= (ImageView) findViewById(R.id.iv_add);
        ivImage= (ImageView) findViewById(R.id.iv_image);
        ivHeader= (ImageView) findViewById(R.id.header);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        mtoolBar= (Toolbar) findViewById(R.id.toolbar);
        mtoolBar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0,getResources().getColor(R.color.colorPrimary)));
        imageviewHeight=180;

        ScrollUtils.addOnGlobalLayoutListener(mScrollView, new Runnable() {
            @Override
            public void run() {
              //  mScrollView.scrollTo(0,130);
                mScrollView.smoothScrollTo(0,0);
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        onScrollChanged(mScrollView.getCurrentScrollY(),false,false);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.colorPrimary);
        float alpha = Math.min(1, (float) scrollY / imageviewHeight);
        mtoolBar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        ViewHelper.setTranslationY(ivImage, scrollY / 2);

        float scale=1+ScrollUtils.getFloat((130-scrollY)/130,0,1);
        ViewHelper.setScaleX(ivHeader,scale);
        ViewHelper.setScaleY(ivHeader,scale);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}

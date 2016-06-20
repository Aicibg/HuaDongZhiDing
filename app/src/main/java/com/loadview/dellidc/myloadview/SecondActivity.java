package com.loadview.dellidc.myloadview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements HoveringScrollView.OnScrollListening {
  private HoveringScrollView hoveringScrollView;
    private int searchLayoutTop;
    private RelativeLayout rlayout;
    private LinearLayout hoveringLayout;
    private LinearLayout serch01,search02;
    private Button btButton;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        inintView();
    }

    private void inintView() {
        view= getLayoutInflater().inflate(R.layout.top_include,null);
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        view.setLayoutParams(params);
        hoveringScrollView= (HoveringScrollView) findViewById(R.id.hoveringscrollview);
        rlayout= (RelativeLayout) findViewById(R.id.rlayout);
        hoveringLayout= (LinearLayout) findViewById(R.id.hoveringlayout);
        search02= (LinearLayout) findViewById(R.id.search02);
        search02.addView(view);
        serch01= (LinearLayout) findViewById(R.id.search01);
        btButton= (Button) findViewById(R.id.bt_button);
        hoveringScrollView.setOnScrollListening(this);

        btButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, hoveringScrollView.getScaleY()+"",Toast.LENGTH_SHORT).show();
                Log.i("test","getScaleY()="+hoveringScrollView.getScaleY());
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            searchLayoutTop=rlayout.getBottom();
        }
    }

    @Override
    public void onScroll(int scrolly) {
       if(scrolly>=searchLayoutTop){
           if(view.getParent()!=serch01){
               search02.removeView(view);
               serch01.addView(view);
//           if(serch01.getVisibility()== View.GONE&search02.getVisibility()==View.VISIBLE) {
//                   search02.setVisibility(View.GONE);
//                   serch01.setVisibility(View.VISIBLE);
           }
           }else{
               if(view.getParent()!=search02){
                   serch01.removeView(view);
                   search02.addView(view);
               }
//           if(serch01.getVisibility()== View.VISIBLE&search02.getVisibility()==View.GONE) {
//               search02.setVisibility(View.VISIBLE);
//               serch01.setVisibility(View.GONE);
//           }
           }
       }

}

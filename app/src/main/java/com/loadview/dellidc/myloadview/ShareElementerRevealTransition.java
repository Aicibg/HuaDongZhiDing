package com.loadview.dellidc.myloadview;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

/**
 * Created by Dellidc on 2016/6/15.
 */

@TargetApi(Build.VERSION_CODES.KITKAT)
public class ShareElementerRevealTransition extends Transition {
    private static final String PROPNAME_RADIUS="custom_reveal:change_radius:radius";
    private boolean hasAnimal=false;
    private View animView;

    public ShareElementerRevealTransition( View animView) {
        this.animView = animView;
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
       transitionValues.values.put(PROPNAME_RADIUS,transitionValues.view.getWidth()/2);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
       View view=transitionValues.view;
        float widthSquared=view.getWidth()*view.getWidth();
        float heightSquared=view.getHeight()*view.getHeight();
        int radius=(int) Math.sqrt(widthSquared+heightSquared)/2;
        transitionValues.values.put(PROPNAME_RADIUS,radius);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
       if(startValues==null&endValues==null){
           return null;
       }
        final View view=endValues.view;
        int startRaduius= (int) startValues.values.get(PROPNAME_RADIUS);
        int endRadius= (int) endValues.values.get(PROPNAME_RADIUS);

        if(view==animView){
            Animator reveal=createAnimator(view,startRaduius,endRadius);
            hasAnimal=true;
            return reveal;
        }
        return null;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private Animator createAnimator(View view, float startRaduius, float endRadius) {
        int centerX=view.getWidth()/2;
        int centerY=view.getHeight()/2;
        Animator reveal= ViewAnimationUtils.createCircularReveal(view,centerX,centerY,startRaduius,endRadius);

        return reveal;
    }
}

package com.zhizhen.ybb.my;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

import com.zhizhen.ybb.R;
import com.zhizhen.ybb.WaveView;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：tc on 2017/5/11.
 * 邮箱：qw805880101@qq.com
 * 版本：v1.0
 */
public class MyActivity extends Activity {

    //    private CostomRound costomRound;
//
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mWaveView.setText(a + "%");
            if (a != 100) {
                mWaveView.setTextState("正在绑定...");
            } else {

                mWaveView.setTextState("绑定成功");
                mAnimatorSet.cancel();
            }

        }
    };

    private int a = 0;

    private WaveView mWaveView;

    private AnimatorSet mAnimatorSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);

        mWaveView = (WaveView) findViewById(R.id.costom_round);

        mWaveView.setWaveColor(
                Color.parseColor("#4078caf4"),
                Color.parseColor("#503faefd"));

        mWaveView.setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        initAnimation();
        mWaveView.setShowWave(true);
        if (mAnimatorSet != null) {
            mAnimatorSet.start();
        }

//
        new Thread() {
            @Override
            public void run() {

                while (a < 100) {
                    try {
                        if (a == 50) {
                            Thread.sleep(2000);
                        }
                        Thread.sleep(50);
                        mHandler.sendEmptyMessage(0);
                        a++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    private void initAnimation() {
        List<Animator> animators = new ArrayList<>();

        // horizontal animation.
        // wave waves infinitely.
        ObjectAnimator waveShiftAnim = ObjectAnimator.ofFloat(
                mWaveView, "waveShiftRatio", 0f, 1f);
        waveShiftAnim.setRepeatCount(ValueAnimator.INFINITE);
        waveShiftAnim.setDuration(1000);
        waveShiftAnim.setInterpolator(new LinearInterpolator());
        animators.add(waveShiftAnim);

        // vertical animation.
        // water level increases from 0 to center of WaveView
        ObjectAnimator waterLevelAnim = ObjectAnimator.ofFloat(
                mWaveView, "waterLevelRatio", 0.4f, 0.4f);
        waterLevelAnim.setDuration(10000);
        waterLevelAnim.setInterpolator(new DecelerateInterpolator());
        animators.add(waterLevelAnim);

        // amplitude animation.
        // wave grows big then grows small, repeatedly
        ObjectAnimator amplitudeAnim = ObjectAnimator.ofFloat(
                mWaveView, "amplitudeRatio", 0.0001f, 0.05f);
        amplitudeAnim.setRepeatCount(ValueAnimator.INFINITE);
        amplitudeAnim.setRepeatMode(ValueAnimator.REVERSE);
        amplitudeAnim.setDuration(5000);
        amplitudeAnim.setInterpolator(new LinearInterpolator());
        animators.add(amplitudeAnim);

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.playTogether(animators);
    }

}

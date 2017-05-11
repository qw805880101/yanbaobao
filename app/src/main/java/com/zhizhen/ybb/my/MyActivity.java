package com.zhizhen.ybb.my;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.zhizhen.ybb.CostomRound;
import com.zhizhen.ybb.R;

/**
 * 作者：tc on 2017/5/11.
 * 邮箱：qw805880101@qq.com
 * 版本：v1.0
 */
public class MyActivity extends Activity {

    private CostomRound costomRound;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            costomRound.setText(a + "%");
            if (a != 100){
                costomRound.setTextState("正在绑定...");
            } else {
                costomRound.setTextState("绑定成功");
            }

        }
    };

    private int a = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding);

        costomRound = (CostomRound) findViewById(R.id.costom_round);

        new Thread() {
            @Override
            public void run() {

                while (a < 100) {
                    try {
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
}

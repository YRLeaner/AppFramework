package com.example.tyr.newstest.ViewPager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by tyr on 2017/2/19.
 */
public class AutoViewPager extends ViewPager {

    private int currentItem;
    private Timer mTimer;
    private AutoTask mTask;
    private boolean isStart = true;


    public AutoViewPager(Context context) {
        this(context, null);
    }

    public AutoViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void dostart(){
        isStart = true;
        if (mTimer==null){
            mTimer = new Timer();
        }
        mTimer.schedule(new AutoTask(),6000,6000);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            currentItem = getCurrentItem();
            if(currentItem == getAdapter().getCount() - 1){
                currentItem = 0 ;
            }else {
                currentItem++ ;
            }
            setCurrentItem(currentItem);
        }
    };

    private AutoHandler mHandler = new AutoHandler();

    private final static class AutoHandler extends android.os.Handler{}

    public void updatePointView(int size) {
        updatePointView(size,0);
    }

    public void updatePointView(int size,int currentItem) {
        if (getParent() instanceof AutoScrollViewPager){
            AutoScrollViewPager pager = (AutoScrollViewPager) getParent();
            pager.initPointView(size,currentItem);
        }else {
            Log.e("TAG", "parent view not be AutoScrollViewPager");
        }
    }

    public void onPageSelected(int position) {
        if (getParent() == null) {
            return;
        }
        if (getParent() instanceof AutoScrollViewPager) {
            AutoScrollViewPager pager = (AutoScrollViewPager) getParent();
            pager.updatePointView(position);
        }
    }

    public void init(AutoViewPager mViewPager, BaseViewPagerAdapter adapter) {
        adapter.init(mViewPager,adapter);
    }

    private class AutoTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(runnable);
        }
    }

    public void onStop(){
        //先取消定时器
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

    public void onDestroy(){
        onStop();
    }

    public void onResume(){
        start();
    }

    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_UP:
                onResume();
                break;
        }
        return super.onTouchEvent(ev);
    }
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                //onTouchEvent中无法拦截到ACTION_DOWN的动作
                onStop();
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }


    public void start(){
        if (((BaseViewPagerAdapter)getAdapter()).getRealCount() == 0) {
            return;
        }
        //先停止
        onStop();
        dostart();
    }


    public TextView getSubTitle() {
        if (getParent() instanceof AutoScrollViewPager){
            AutoScrollViewPager pager = (AutoScrollViewPager) getParent();
            return pager.getSubTitle();
        }
        return null;
    }

    public boolean isStart() {
        return isStart;
    }

}

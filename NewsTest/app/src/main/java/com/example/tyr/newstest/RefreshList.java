package com.example.tyr.newstest;

import android.animation.FloatArrayEvaluator;
import android.content.Context;

import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Date;

/**
 * Created by tyr on 2017/2/22.
 */
public class RefreshList extends ListView implements AbsListView.OnScrollListener {

    private final static int RELEASE_To_REFRESH = 0;
    private final static int PULL_To_REFRESH = 1;
    private final static int REFRESH = 2;
    private final static int DONE = 3;
    private final static int LOADING = 4;

    private final static int RATIO = 3;
    private LayoutInflater inflater;

    private LinearLayout headerView;
    private TextView lvHeaderTipsTv;
    private TextView lvHeaderLastUpdatedTv;
    private ImageView lvHeaderArrowIv;
    private ProgressBar lvHeaderProgressBar;

    private int headerContentHeight;
    private RotateAnimation animation;
    private RotateAnimation reverseanimation;

    private int startY;
    private int state;
    private boolean isBack;

    private boolean isRecored;
    private OnRefreshListener refreshListener;

    private boolean isRefreshable;

    public RefreshList(Context context){
        super(context);
        init(context);
    }

    public RefreshList(Context context,AttributeSet attrs){
        super(context,attrs);
        init(context);
    }

    private void init(Context context) {
        //setCacheColorHint(context.getResources().getColor(R.id.trans));
        inflater = LayoutInflater.from(context);
        headerView = (LinearLayout)inflater.inflate(R.layout.headerlayout, null);
        lvHeaderTipsTv = (TextView)headerView.findViewById(R.id.header_text);
        lvHeaderLastUpdatedTv  =(TextView)headerView.findViewById(R.id.header_lvtext);
        lvHeaderArrowIv = (ImageView)headerView.findViewById(R.id.header_arrow);

        lvHeaderArrowIv.setMinimumWidth(70);
        lvHeaderArrowIv.setMinimumHeight(50);

        lvHeaderProgressBar = (ProgressBar)headerView.findViewById(R.id.header_progressbar);
        measureView(headerView);
        headerContentHeight = headerView.getMeasuredHeight();
        headerView.setPadding(0,-1*headerContentHeight,0,0);
        headerView.invalidate();
        addHeaderView(headerView, null, false);

        setOnScrollListener(this);

        animation = new RotateAnimation(0,-180,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        animation.setInterpolator(new LinearInterpolator());
        animation.setDuration(250);
        animation.setFillAfter(true);

        reverseanimation = new RotateAnimation(-180,0,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        reverseanimation.setInterpolator(new LinearInterpolator());
        reverseanimation.setDuration(200);
        reverseanimation.setFillAfter(true);

        state = DONE;
        isRefreshable = false;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem==0){
            isRefreshable = true;
        }else {
            isRefreshable = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isRefreshable){
            switch (ev.getAction()){
                case MotionEvent.ACTION_DOWN:
                    if (!isRecored){
                        isRecored  =true;
                        startY = (int)ev.getY();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if (state!=REFRESH&&state!=LOADING){
                        if (state==PULL_To_REFRESH){
                            state = DONE;
                            changeHeaderViewByState();
                        }
                        if (state==RELEASE_To_REFRESH){
                            state  =REFRESH;
                            changeHeaderViewByState();
                            onLvRefresh();
                        }
                    }
                    isRecored = false;
                    isBack = false;
                    break;
                case MotionEvent.ACTION_MOVE:
                    int tempY = (int)ev.getY();
                    if (!isRecored){
                        isRecored  =true;
                        startY = tempY;
                    }
                    if (state!=REFRESH&&isRecored&&state!=LOADING){
                        if (state==RELEASE_To_REFRESH){
                            setSelection(0);
                            if(((tempY-startY)/RATIO<headerContentHeight)&&(tempY-startY)>0){
                                state = PULL_To_REFRESH;
                                changeHeaderViewByState();
                            }else if (tempY-startY<=0){
                                state = DONE;
                                changeHeaderViewByState();
                            }
                        }
                        if (state==PULL_To_REFRESH){
                            setSelection(0);
                            if((tempY-startY)/RATIO<headerContentHeight){
                                state = RELEASE_To_REFRESH;
                                isBack = true;
                                changeHeaderViewByState();
                            }else if (tempY-startY<=0){
                                state = DONE;
                                changeHeaderViewByState();
                            }
                        }
                        if (state==DONE){
                            if (tempY-startY>0){
                                state = PULL_To_REFRESH;
                                changeHeaderViewByState();
                            }
                        }

                        if (state==PULL_To_REFRESH){
                            headerView.setPadding(0,-1*headerContentHeight+(tempY-startY)/RATIO,0,0);
                        }
                        if (state==RELEASE_To_REFRESH){
                            headerView.setPadding(0,(tempY-startY)/RATIO-headerContentHeight,0,0);
                        }

                    }
                    break;
                default:
                    break;
            }
        }
        return super.onTouchEvent(ev);
    }

    private void changeHeaderViewByState() {
        switch (state){
            case RELEASE_To_REFRESH:
                lvHeaderArrowIv.setVisibility(View.VISIBLE);
                lvHeaderProgressBar.setVisibility(View.GONE);
                lvHeaderTipsTv.setVisibility(View.VISIBLE);
                lvHeaderLastUpdatedTv.setVisibility(View.VISIBLE);

                lvHeaderArrowIv.clearAnimation();
                lvHeaderArrowIv.startAnimation(animation);
                lvHeaderTipsTv.setText("松开刷新");
                break;
            case PULL_To_REFRESH:
                lvHeaderProgressBar.setVisibility(View.GONE);
                lvHeaderTipsTv.setVisibility(View.VISIBLE);
                lvHeaderLastUpdatedTv.setVisibility(View.VISIBLE);
                lvHeaderArrowIv.clearAnimation();
                lvHeaderArrowIv.setVisibility(View.VISIBLE);
                if (isBack){
                    isBack = false;
                    lvHeaderArrowIv.clearAnimation();
                    lvHeaderArrowIv.startAnimation(reverseanimation);
                    lvHeaderTipsTv.setText("下拉刷新");
                }else {
                    lvHeaderTipsTv.setText("下拉刷新");
                }
                break;
            case REFRESH:
                headerView.setPadding(0, 0, 0, 0);
                lvHeaderProgressBar.setVisibility(VISIBLE);
                lvHeaderArrowIv.clearAnimation();
                lvHeaderArrowIv.setVisibility(GONE);
                lvHeaderTipsTv.setText("正在刷新");
                lvHeaderLastUpdatedTv.setVisibility(VISIBLE);
                break;
            case DONE:
                headerView.setPadding(0, -1 * headerContentHeight, 0, 0);
                lvHeaderProgressBar.setVisibility(GONE);
                lvHeaderArrowIv.clearAnimation();
                lvHeaderArrowIv.setImageResource(R.drawable.pull_to_refresh_arrow);
                lvHeaderTipsTv.setText("下拉刷新");
                lvHeaderLastUpdatedTv.setVisibility(VISIBLE);
                break;
        }
    }

    private void measureView(View child) {
        ViewGroup.LayoutParams params = child.getLayoutParams();
        if (params==null){
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0,0+0,params.width);
        int lpHeight = params.height;
        int childHeightSpec ;
        if (lpHeight>0){
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,MeasureSpec.EXACTLY);
        }else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec,childHeightSpec);
    }

    public void setonRefreshListener(OnRefreshListener refreshListener){
        this.refreshListener = refreshListener;
        isRefreshable = true;
    }

    public interface OnRefreshListener{
        public void onRefresh();
    }

    public void onRefreshComplete(){
        state = DONE;
        lvHeaderLastUpdatedTv.setText("最近更新："+new Date().toLocaleString());
        changeHeaderViewByState();
    }

    private void onLvRefresh(){
        if (refreshListener!=null){
            refreshListener.onRefresh();
        }
    }

    public void setAdapter(CnblogListAdapter adapter){
        lvHeaderLastUpdatedTv.setText("最近更新"+new Date().toLocaleString());
        super.setAdapter(adapter);
    }




}

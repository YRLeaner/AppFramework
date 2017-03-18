package com.example.tyr.newstest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;

/**
 * Created by tyr on 2017/2/19.
 */
public class WelcomeActivity extends Activity {
    private AlphaAnimation start_anima;
    private SharedPreferences sharedPreferences;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(this,R.layout.welcome_layout,null);
        getSharedPreference();
    }
    private void getSharedPreference(){
        sharedPreferences = getSharedPreferences("firstsubmit", Context.MODE_PRIVATE);
        boolean isfirst = sharedPreferences.getBoolean("isfirst",false);
        if (!isfirst){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isfirst", true);
            editor.commit();
            setContentView(view);
            initDate();
        }else {
            redirectTo();
        }
    }

    private void initDate() {
        start_anima = new AlphaAnimation(0.3f, 1.0f);
        start_anima.setDuration(2000);
        view.startAnimation(start_anima);
        start_anima.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // TODO Auto-generated method stub
                redirectTo();
            }
        });
    }



    private void redirectTo() {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}

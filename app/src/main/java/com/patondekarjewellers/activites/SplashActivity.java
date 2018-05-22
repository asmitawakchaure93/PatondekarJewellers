package com.patondekarjewellers.activites;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.patondekarjewellers.R;
import com.patondekarjewellers.utils.AppSession;
import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.ScaleModifier;

import java.util.Locale;


public class SplashActivity extends AppCompatActivity
{
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    ImageView ivGoldNecklace;
    LinearLayout llParent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        llParent = (LinearLayout) findViewById(R.id.llParent);
        ivGoldNecklace = (ImageView) findViewById(R.id.ivGoldNecklace);
        //   ivGoldNecklace.setOnClickListener(this);
        /*ivGoldNecklace.post(new Runnable(){
            @Override
            public void run() {
                ivGoldNecklace.performClick();
            }
        });*/

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startSplash();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void startSplash()
    {

        if (AppSession.getInstance().getLang(this).equals(""))
        {
            changeLanguage("en");
        } else
        {
            changeLanguage(AppSession.getInstance().getLang(SplashActivity.this));
        }
        if (AppSession.getInstance().getLoginStatus(SplashActivity.this))
        {
            Intent home_intent = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(home_intent);
            overridePendingTransition(R.anim.fade_in_act, R.anim.fade_out_act);
            finish();
        } else
        {
            Intent mainIntent = new Intent(SplashActivity.this,
                    RegistrationActivity.class);
            startActivity(mainIntent);
            finish();
            overridePendingTransition(R.anim.fade_in_act, R.anim.fade_out_act);
        }
    }

   /* @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.ivGoldNecklace:
                new ParticleSystem(SplashActivity.this, 15, R.drawable.star_gold, 1000)
                        .setSpeedRange(0.1f, 0.25f)
                        .emit(view, 15,2000);

                break;
        }

    }*/

    private void changeLanguage(String language)
    {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        Intent home_intent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(home_intent);
        finish();
    }
}

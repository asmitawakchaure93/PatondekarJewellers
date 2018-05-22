package com.patondekarjewellers.activites;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.patondekarjewellers.R;
import com.patondekarjewellers.utils.AppSession;

import java.util.Locale;

public class LanguageSelectionActivity extends AppCompatActivity implements View.OnClickListener
{
    TextView tvEnglish, tvMarathi;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);

        tvEnglish = (TextView) findViewById(R.id.tvEnglish);
        tvEnglish.setOnClickListener(this);

        tvMarathi = (TextView) findViewById(R.id.tvMarathi);
        tvMarathi.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.tvEnglish:
                changeLanguage("en");
                AppSession.getInstance().saveLang(LanguageSelectionActivity.this, "en");
                break;

            case R.id.tvMarathi:
                changeLanguage("mr");
                AppSession.getInstance().saveLang(LanguageSelectionActivity.this, "mr");
                break;
        }
    }

    private void changeLanguage(String language)
    {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        Intent home_intent = new Intent(LanguageSelectionActivity.this, HomeActivity.class);
        startActivity(home_intent);
        finish();
    }
}

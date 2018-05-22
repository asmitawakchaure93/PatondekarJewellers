package com.patondekarjewellers.activites;

import android.os.Bundle;

import com.patondekarjewellers.R;

/**
 * Created by Asmita on 12/6/2017.
 */

public class TermsConditionsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_conditions);
        setActionbarTitle(getString(R.string.terms_conditions));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onBackButtonPress();
    }
}

package com.patondekarjewellers.activites;

import android.os.Bundle;

import com.patondekarjewellers.R;

/**
 * Created by Asmita on 12/6/2017.
 */

public class ContactUsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        setActionbarTitle(getString(R.string.contact_us));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onBackButtonPress();
    }
}

package com.patondekarjewellers.activites;

import android.os.Bundle;

import com.patondekarjewellers.R;

/**
 * Created by Asmita on 12/6/2017.
 */

public class BankDetailsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);
        setActionbarTitle(getString(R.string.bank_details));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onBackButtonPress();
    }
}

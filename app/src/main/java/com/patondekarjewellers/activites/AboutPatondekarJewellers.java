package com.patondekarjewellers.activites;

import android.os.Bundle;

import com.patondekarjewellers.R;

public class AboutPatondekarJewellers extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_patondekar_jewellers);
        setActionbarTitle(getString(R.string.about_patondekar_jewellers));
    }
}

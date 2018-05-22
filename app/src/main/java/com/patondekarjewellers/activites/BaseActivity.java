package com.patondekarjewellers.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.patondekarjewellers.R;
import com.patondekarjewellers.adapters.SlidingPaneAdapter;
import com.patondekarjewellers.models.SideMenuItem;
import com.patondekarjewellers.utils.CustomSlidingPaneLayout;

import java.util.ArrayList;

/**
 * Created by Akshay on 10-11-2017.
 */

public class BaseActivity extends AppCompatActivity implements View.OnClickListener
{
    CustomSlidingPaneLayout slidingPaneLayout;
    ListView mMenuList;
    TextView tvToolbarTitle;
    ArrayList<SideMenuItem> sideMenuItemList;
    SlidingPaneAdapter slidingPaneAdapter;
    ImageView ivSideMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(int layoutResID)
    {
        super.setContentView(R.layout.activity_base);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.container);
        viewGroup.removeAllViews();
        View view = getLayoutInflater().inflate(layoutResID, null);
        viewGroup.addView(view);
        initialise();
    }

    private void initialise()
    {
        slidingPaneLayout = (CustomSlidingPaneLayout) findViewById(R.id.slidingPaneLayout);
        mMenuList = (ListView) findViewById(R.id.lvMenuList);
        tvToolbarTitle = (TextView) findViewById(R.id.tvToolbarTitle);
        ivSideMenu = (ImageView) findViewById(R.id.ivSideMenu);

        if (sideMenuItemList == null)
        {
            sideMenuItemList = new ArrayList<SideMenuItem>();
        }
        slidingPaneLayout.setPanelSlideListener(panelListener);
        slidingPaneLayout.setParallaxDistance(200);

        sideMenuItemList.add(new SideMenuItem(getString(R.string.dashboard), R.drawable.ic_home));
        sideMenuItemList.add(new SideMenuItem(getString(R.string.about_patondekar_jewellers), R.drawable.ic_about_us));
        sideMenuItemList.add(new SideMenuItem(getString(R.string.contact_us), R.drawable.ic_contact_us));
        sideMenuItemList.add(new SideMenuItem(getString(R.string.bank_details), R.drawable.ic_bank_details));
        sideMenuItemList.add(new SideMenuItem(getString(R.string.terms_conditions), R.drawable.ic_terms_conditions));
        sideMenuItemList.add(new SideMenuItem(getString(R.string.about_us), R.drawable.ic_about_pj));
        sideMenuItemList.add(new SideMenuItem(getString(R.string.settings), R.drawable.ic_preference));


        slidingPaneAdapter = new SlidingPaneAdapter(this, R.layout.activity_base, sideMenuItemList);
        mMenuList.setAdapter(slidingPaneAdapter);

        mMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                switch (position)
                {
                    case 0:
                        slidingPaneLayout.closePane();
                        Intent home_intent = new Intent(BaseActivity.this, HomeActivity.class);
                        startActivity(home_intent);
                        overridePendingTransition(R.anim.fade_in_act, R.anim.fade_out_act);
                        finish();
                        break;

                    case 1:
                        slidingPaneLayout.closePane();
                        Intent about_patondekar_intent = new Intent(BaseActivity.this, AboutPatondekarJewellers.class);
                        startActivity(about_patondekar_intent);
                        overridePendingTransition(R.anim.fade_in_act, R.anim.fade_out_act);
                        finish();
                        break;


                    case 2:
                        slidingPaneLayout.closePane();
                        Intent contact_intent = new Intent(BaseActivity.this, ContactUsActivity.class);
                        startActivity(contact_intent);
                        overridePendingTransition(R.anim.fade_in_act, R.anim.fade_out_act);
                        finish();
                        break;

                    case 3:
                        slidingPaneLayout.closePane();
                        Intent support_intent = new Intent(BaseActivity.this, BankDetailsActivity.class);
                        startActivity(support_intent);
                        overridePendingTransition(R.anim.fade_in_act, R.anim.fade_out_act);
                        finish();
                        break;


                    case 4:
                        slidingPaneLayout.closePane();
                        Intent terms_conditions_intent = new Intent(BaseActivity.this, TermsConditionsActivity.class);
                        startActivity(terms_conditions_intent);
                        overridePendingTransition(R.anim.fade_in_act, R.anim.fade_out_act);
                        finish();
                        break;

                    case 5:
                        slidingPaneLayout.closePane();
                        Intent about_intent = new Intent(BaseActivity.this, AboutUsActivity.class);
                        startActivity(about_intent);
                        overridePendingTransition(R.anim.fade_in_act, R.anim.fade_out_act);
                        finish();
                        break;

                    case 6:
                        slidingPaneLayout.closePane();
                        Intent language_selection_intent = new Intent(BaseActivity.this, SettingsActivity.class);
                        startActivity(language_selection_intent);
                        overridePendingTransition(R.anim.fade_in_act, R.anim.fade_out_act);
                        finish();
                        break;

                }
            }
        });

        ivSideMenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                slidingPaneLayout.openPane();
            }
        });
    }

    CustomSlidingPaneLayout.PanelSlideListener panelListener = new CustomSlidingPaneLayout.PanelSlideListener()
    {

        @Override
        public void onPanelSlide(View panel, float slideOffset)
        {

        }

        @Override
        public void onPanelClosed(View arg0)
        {
            ivSideMenu.animate().rotation(0);
        }

        @Override
        public void onPanelOpened(View arg0)
        {
            ivSideMenu.animate().rotation(90);

        }
    };

    public void setActionbarTitle(String value)
    {
        tvToolbarTitle.setVisibility(View.VISIBLE);
        tvToolbarTitle.setText(value);
    }


    @Override
    public void onClick(View v)
    {
    }

    public void onBackButtonPress()
    {
        Intent home_intent = new Intent(BaseActivity.this, HomeActivity.class);
        startActivity(home_intent);
        overridePendingTransition(R.anim.fade_in_act, R.anim.fade_out_act);
        finish();
    }
}

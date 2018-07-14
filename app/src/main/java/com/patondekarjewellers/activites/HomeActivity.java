package com.patondekarjewellers.activites;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.patondekarjewellers.R;
import com.patondekarjewellers.interfaces.NetworkAPI;
import com.patondekarjewellers.utils.AppSession;
import com.patondekarjewellers.utils.StringConverter;
import com.patondekarjewellers.webservices.HttpHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class HomeActivity extends BaseActivity implements View.OnClickListener
{
    TextView tvgoldCurrentDate, tvCurrentGoldRate, tvCurrentDollarDate, tvGoldSpotRate,
            tvGoldMumbai995RTGS, tvGoldMumbai9950WithGST, tvCurrentDollarRate, tvMarquee, tvJalgoanRate, tvJalgoanGSTRate;
    CardView cvGoldSpot, cvGoldMumbai995RTGS, cvGoldMumbai9950WithGST, cvJalgoanGold, cvJalgoanGSTGold;
    boolean isFirstTime = false;
    private float currentSpotPrice = 0;
    private float current95Price = 0;
    private float current90Price = 0;
    int differenceValue;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_new);
        initialise();

    }

    private void initialise()
    {
        tvgoldCurrentDate = (TextView) findViewById(R.id.tvgoldCurrentDate);
        tvCurrentGoldRate = (TextView) findViewById(R.id.tvCurrentGoldRate);
        tvCurrentDollarDate = (TextView) findViewById(R.id.tvCurrentDollarDate);
        tvGoldSpotRate = (TextView) findViewById(R.id.tvGoldSpotRate);
        tvGoldMumbai995RTGS = (TextView) findViewById(R.id.tvGoldMumbai995RTGS);
        tvGoldMumbai9950WithGST = (TextView) findViewById(R.id.tvGoldMumbai9950WithGST);
        tvCurrentDollarRate = (TextView) findViewById(R.id.tvCurrentDollarRate);
        tvMarquee = (TextView) findViewById(R.id.tvMarquee);
        cvGoldSpot = (CardView) findViewById(R.id.cvGoldSpot);
        cvGoldSpot.setBackgroundResource(R.drawable.gradient_transparent_color);
        cvGoldMumbai995RTGS = (CardView) findViewById(R.id.cvGoldMumbai995RTGS);
        cvGoldMumbai9950WithGST = (CardView) findViewById(R.id.cvGoldMumbai9950WithGST);
        cvJalgoanGold = (CardView) findViewById(R.id.cvJalgoanGold);
        tvJalgoanRate = (TextView) findViewById(R.id.tvJalgoanRate);
        tvJalgoanGSTRate = (TextView) findViewById(R.id.tvJalgoanGSTRate);
        cvJalgoanGSTGold = (CardView) findViewById(R.id.cvJalgoanGSTGold);

        tvMarquee.setText(getString(R.string.rate_valid_for_ornaments) + "   " + getString(R.string.rate_valid_for_ornaments));
        tvMarquee.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tvMarquee.setSingleLine(true);
        tvMarquee.setSelected(true);

        //This method was required initially to set the current Date
        //getMonth();
        callService();
        /*if (AppSession.getInstance().getDollarPrice(HomeActivity.this).equalsIgnoreCase(""))
        {
            getDollarRates(HomeActivity.this);
        } else
        {
            tvCurrentDollarRate.setText(getString(R.string.inr) + "  " + AppSession.getInstance().getDollarPrice(HomeActivity.this));
        }*/

    }


    private void getMonth()
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        String monthName = "";

        switch (month)
        {
            case 0:
                monthName = "JAN";
                break;

            case 1:
                monthName = "FEB";
                break;

            case 2:
                monthName = "MARCH";
                break;

            case 3:
                monthName = "APRIL";
                break;

            case 4:
                monthName = "MAY";
                break;

            case 5:
                monthName = "JUNE";
                break;

            case 6:
                monthName = "JULY";
                break;

            case 7:
                monthName = "AUG";
                break;

            case 8:
                monthName = "SEPT";
                break;

            case 9:
                monthName = "OCT";
                break;

            case 10:
                monthName = "NOV";
                break;

            case 11:
                monthName = "DEC";
                break;


        }
        tvgoldCurrentDate.setText(calendar.get(Calendar.DATE) + " " + monthName + " " + calendar.get(Calendar.YEAR));
        tvCurrentDollarDate.setText(calendar.get(Calendar.DATE) + " " + monthName + " " + calendar.get(Calendar.YEAR));


    }

    private void callService()
    {

        //This method is called 2 webservices one to get the difference amount and second to
        // get the Live Gold Rates

        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            public void run()
            {
                getPatondekarGoldDifference(HomeActivity.this);
                getLiveRates(HomeActivity.this);
            }
        }, 0, 5000);
    }

    private void getPatondekarGoldDifference(Context context)
    {
        if (HttpHelper.isNetworkAvailable(context))
        {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(HttpHelper.GOLD_VALUE_BASE_URL)
                    .build();

            NetworkAPI api = restAdapter.create(NetworkAPI.class);
            api.getGoldDifference(new Callback<JsonObject>()
            {
                @Override
                public void success(JsonObject responseObject, Response response)
                {
                    try
                    {
                        JSONObject jsonObject = new JSONObject(responseObject.toString());
                        differenceValue = jsonObject.optInt("Value");


                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failure(RetrofitError error)
                {
                    error.printStackTrace();

                }
            });


        }
    }

    private void getDollarRates(Context context)
    {

        if (HttpHelper.isNetworkAvailable(context))
        {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(HttpHelper.DOLLAR_BASE_URL)
                    .build();

            NetworkAPI api = restAdapter.create(NetworkAPI.class);

            api.getDollarRates(new Callback<JsonObject>()

            {
                @Override
                public void success(JsonObject responseObject, Response response)
                {
                    try
                    {
                        JSONObject jsonObject = new JSONObject(responseObject.toString());
                        JSONObject ratesObject = jsonObject.optJSONObject("quotes");

                        String inrRate = ratesObject.optString("USDINR");
                        tvCurrentDollarRate.setText(getString(R.string.inr) + "  " + inrRate);
                        AppSession.getInstance().saveDollarPrice(HomeActivity.this, inrRate);

                    } catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                @Override
                public void failure(RetrofitError error)
                {
                    String rates = error.toString();
                    //   Toast.makeText(HomeActivity.this, rates, Toast.LENGTH_SHORT).show();


                }
            });

        }
    }


    private void getLiveRates(Context context)
    {

        if (HttpHelper.isNetworkAvailable(context))
        {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(HttpHelper.BASE_URL)
                    .setConverter(new StringConverter())
                    .build();

            NetworkAPI api = restAdapter.create(NetworkAPI.class);
            long timeStamp = System.currentTimeMillis();

            api.getLiveRates(timeStamp, new Callback<String>()

            {
                @Override
                public void success(String s, Response response)
                {
                    String rates[] = s.split(",");
                    String goldRate = rates[22];
                    tvCurrentGoldRate.setText(getString(R.string.rupee) + " " + goldRate);

                    String spotgoldRate = rates[34];
                    tvGoldSpotRate.setText(getString(R.string.rupee) + " " + spotgoldRate);

                    String goldMumbaiRTGStwo = rates[3];
                    tvGoldMumbai995RTGS.setText(getString(R.string.rupee) + " " + goldMumbaiRTGStwo);
                    current95Price = Float.parseFloat((goldMumbaiRTGStwo));

                    String goldhalfgmWithGSTtwo = rates[19];
                    tvGoldMumbai9950WithGST.setText(getString(R.string.rupee) + " " + goldhalfgmWithGSTtwo);
                    current90Price = Float.parseFloat((goldMumbaiRTGStwo));

                    String value = String.valueOf(differenceValue);
                    int patondekarRates;
                    if (value.contains("-"))
                    {
                        patondekarRates = Integer.parseInt(goldMumbaiRTGStwo) - differenceValue;
                    } else
                    {
                        patondekarRates = Integer.parseInt(goldMumbaiRTGStwo) + differenceValue;
                    }

                    String newJalgoanRates = String.valueOf(patondekarRates);
                    tvJalgoanRate.setText(getString(R.string.rupee) + " " + newJalgoanRates);
                    currentSpotPrice = Float.parseFloat((newJalgoanRates));

                    /*int patondekarGSTRates;
                    if (value.contains("-"))
                    {
                        patondekarGSTRates = Integer.parseInt(goldhalfgmWithGSTtwo) - differenceValue;
                    } else

                    {
                        patondekarGSTRates = Integer.parseInt(goldhalfgmWithGSTtwo) + differenceValue;
                    }*/

                    //int jalgoanGSTRates = Integer.parseInt(goldhalfgmWithGSTtwo) - differenceValue;


                    //Gold Rates with GST 3% of actual amount
                    int valueWithGST = (int)(patondekarRates*(3.0f/100.0f));

                    int addGST = patondekarRates + valueWithGST;

                    String newJalgoanGSTRates = String.valueOf(addGST);

                    tvJalgoanGSTRate.setText(getString(R.string.rupee) + " " + newJalgoanGSTRates);


                    if (isFirstTime)
                    {
                        //current Spot price
                        if (currentSpotPrice > AppSession.getInstance().getGoldPrice(HomeActivity.this))
                        {
                            cvJalgoanGold.setBackgroundResource(R.drawable.gradient_red_color);
                            cvJalgoanGSTGold.setBackgroundResource(R.drawable.gradient_red_color);
                        }

                        if (currentSpotPrice < AppSession.getInstance().getGoldPrice(HomeActivity.this))
                        {

                            cvJalgoanGold.setBackgroundResource(R.drawable.gradient_green_color);
                            cvJalgoanGSTGold.setBackgroundResource(R.drawable.gradient_green_color);
                        }

                        if (currentSpotPrice == AppSession.getInstance().getGoldPrice(HomeActivity.this))
                        {
                            cvJalgoanGold.setBackgroundResource(R.drawable.gradient_transparent_color);
                            cvJalgoanGSTGold.setBackgroundResource(R.drawable.gradient_transparent_color);
                        }


                        //current 95 price
                        if (current95Price > AppSession.getInstance().get95GoldPrice(HomeActivity.this))
                        {
                            cvGoldMumbai995RTGS.setBackgroundResource(R.drawable.gradient_red_color);
                        }

                        if (current95Price < AppSession.getInstance().get95GoldPrice(HomeActivity.this))
                        {
                            cvGoldMumbai995RTGS.setBackgroundResource(R.drawable.gradient_green_color);
                        }

                        if (current95Price == AppSession.getInstance().get95GoldPrice(HomeActivity.this))
                        {
                            cvGoldMumbai995RTGS.setBackgroundResource(R.drawable.gradient_transparent_color);
                        }

                        //current 90 price
                        if (current90Price > AppSession.getInstance().get90GoldPrice(HomeActivity.this))
                        {
                            cvGoldMumbai9950WithGST.setBackgroundResource(R.drawable.gradient_red_color);
                        }

                        if (current90Price < AppSession.getInstance().get90GoldPrice(HomeActivity.this))
                        {
                            cvGoldMumbai9950WithGST.setBackgroundResource(R.drawable.gradient_green_color);
                        }

                        if (current90Price == AppSession.getInstance().get90GoldPrice(HomeActivity.this))
                        {
                            cvGoldMumbai9950WithGST.setBackgroundResource(R.drawable.gradient_transparent_color);
                        }
                    }

                    AppSession.getInstance().saveGoldPrice(HomeActivity.this, currentSpotPrice);
                    AppSession.getInstance().save95GoldPrice(HomeActivity.this, current95Price);
                    AppSession.getInstance().save90GoldPrice(HomeActivity.this, current90Price);
                    isFirstTime = true;

                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            cvGoldSpot.setBackgroundResource(R.drawable.gradient_transparent_color);
                            cvGoldMumbai995RTGS.setBackgroundResource(R.drawable.gradient_transparent_color);
                            cvGoldMumbai9950WithGST.setBackgroundResource(R.drawable.gradient_transparent_color);
                            cvJalgoanGold.setBackgroundResource(R.drawable.gradient_transparent_color);
                            cvJalgoanGSTGold.setBackgroundResource(R.drawable.gradient_transparent_color);
                        }
                    }, 1000);


                }

                @Override
                public void failure(RetrofitError error)
                {
                    String rates = error.toString();

                }
            });

        }
    }


}

package com.patondekarjewellers.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Asmita on 5/7/2017.
 */

public class AppSession
{
    private static final String TAG = "tag";
    private static SharedPreferences sharedPreferences;
    private static AppSession appSession;
    private static String SAVE_DEVICE_ID = "save_device_id";
    private static String IS_LOGIN = "is_login";
    private static String SAVE_USER_ID = "user_id";
    private static String SAVE_USERNAME = "username";
    private static String SAVE_EMAIL = "email";
    private static String SAVE_CONTACT_NUMBER = "number";
    private static String SAVE_SPOT_GOLD_PRICE = "spotgoldprice";
    private static String SAVE_DOLLAR_PRICE = "currentdollarprice";
    private static String SAVE_95_GOLD_PRICE = "95goldprice";
    private static String SAVE_90_GOLD_PRICE = "90goldprice";

    private static String SAVE_LANGUAGE = "save_lang";


    public static AppSession getInstance()
    {
        if (sharedPreferences == null)
        {
            appSession = new AppSession();
        }
        return appSession;
    }

    public void clearSharedPreference(Context context)
    {
        String deviceId = getDeviceId(context);
        SharedPreferences sharedPreferences;
        SharedPreferences.Editor editor;
        sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        saveDeviceId(context, deviceId);
    }

    /* Save Login details*/
    public void saveLoginStatus(Context context, boolean isLogin)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.commit();
    }


    public boolean getLoginStatus(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }


    /*Save device Id*/
    public void saveDeviceId(Context context, String deviceId)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVE_DEVICE_ID, deviceId);
        editor.commit();
    }

    public String getDeviceId(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SAVE_DEVICE_ID, "");
    }

      /*Save user id */

    public void saveId(Context context, String userId)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVE_USER_ID, userId);
        editor.commit();
    }

    public String getUserId(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SAVE_USER_ID, "");

    }

    /*Save Current Gold Price */
    public void saveGoldPrice(Context context, float goldPrice)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(SAVE_SPOT_GOLD_PRICE, goldPrice);
        editor.commit();
    }

    public float getGoldPrice(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(SAVE_SPOT_GOLD_PRICE, 0);

    }

    /*Save Gold95FirstTime Gold Price */
    public void save95GoldPrice(Context context, float goldPrice)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(SAVE_95_GOLD_PRICE, goldPrice);
        editor.commit();
    }

    public float get95GoldPrice(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(SAVE_95_GOLD_PRICE, 0);

    }

    /*Save Gold90FirstTime Gold Price */
    public void save90GoldPrice(Context context, float goldPrice)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(SAVE_90_GOLD_PRICE, goldPrice);
        editor.commit();
    }

    public float get90GoldPrice(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sharedPreferences.getFloat(SAVE_90_GOLD_PRICE, 0);

    }

     /*Save Current Dollar Price */

    public void saveDollarPrice(Context context, String dollarPrice)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVE_DOLLAR_PRICE, dollarPrice);
        editor.commit();
    }

    public String getDollarPrice(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SAVE_DOLLAR_PRICE, "");

    }


     /*Save user email */

    public void saveEmail(Context context, String username)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVE_EMAIL, username);
        editor.commit();
    }

    public String getEmail(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SAVE_EMAIL, "");

    }


     /*Save user contact number */

    public void saveContact(Context context, String username)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVE_CONTACT_NUMBER, username);
        editor.commit();
    }

    public String getContact(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SAVE_CONTACT_NUMBER, "");

    }
       /*Save user lang */

    public void saveLang(Context context, String language)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SAVE_LANGUAGE, language);
        editor.commit();
    }

    public String getLang(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SAVE_LANGUAGE, "");

    }


}

package com.patondekarjewellers.interfaces;

import com.google.android.gms.common.api.Response;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Akshay on 08-11-2017.
 */

public interface NetworkAPI
{
    @GET("/*")
    void getLiveRates(@Query("_") long timestamp, Callback<String> callback);

    @GET("/")
    void getLiveGSTRates(@Query("_") long timestamp, Callback<String> callback);

    @GET("/live?access_key=0c8df1f4804dcc7e845518154dab95ce")
    void getDollarRates(Callback<JsonObject> callback);

    @GET("/value/")
    void getGoldDifference(Callback<JsonObject> response);

}

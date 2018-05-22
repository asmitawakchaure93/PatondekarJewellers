package com.patondekarjewellers.webservices;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Akshay on 08-11-2017.
 */

public class HttpHelper
{
    //LIVE URL
    public static String BASE_URL = "http://spngoldlivebroadcast.noip.us:8888/VOTSBroadcast/Services/xml/GetLiveRateMultipleMatchingTable";

    //http://fastly.glive.in/pu/partners/spn-gold/metadata/bars/15
    public static String GST_BASE_URL = "http://spngoldlivebroadcast.noip.us:7777/VOTSBroadcastStreaming/Services/xml/GetLiveRate";

    public static String DOLLAR_BASE_URL = " http://www.apilayer.net/api/";

    public static String GOLD_VALUE_BASE_URL = "http://115.124.127.89:8174/api/";

    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null)
        {
            return false;
        } else
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
            {
                for (int i = 0; i < info.length; i++)
                {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}

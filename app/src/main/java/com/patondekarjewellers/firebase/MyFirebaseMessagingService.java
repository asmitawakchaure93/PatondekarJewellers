package com.patondekarjewellers.firebase;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.patondekarjewellers.R;
import com.patondekarjewellers.activites.HomeActivity;

/**
 * Created by Akshay on 06-12-2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService
{
    private static final String TAG = "FCM Service";
    private Intent resultIntent;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
       Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getData().get("text"));
        String message =" ";
        String title =" ";
        try {

            message = remoteMessage.getNotification().getBody();
            title = remoteMessage.getNotification().getTitle();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendNotification(title,message);
    }

    private void sendNotification(String title, String message) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.app_logo_notify)
                        .setContentTitle(message)
                        .setOnlyAlertOnce(true)
                        .setContentText(title);

        resultIntent = new Intent(this, HomeActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        mBuilder.setContentIntent(contentIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = mBuilder.build();
        notification.defaults = Notification.DEFAULT_LIGHTS | Notification.FLAG_AUTO_CANCEL;
        int requestID = (int) System.currentTimeMillis();
        mNotificationManager.notify(requestID, notification);


    }


}

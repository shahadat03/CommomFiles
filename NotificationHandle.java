package com.maxproit.ticketBari.utillity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;
import com.maxproit.ticketBari.R;
import com.maxproit.ticketBari.view.common.activity.MainActivity;
import com.maxproit.ticketBari.view.common.activity.MyTripActivity;

public class NotificationHandle {

    private static final String BASIC = "Basic";
    private Context context;
    private String title, message, type, id;
    private NotificationManager manager;
    String CHANNEL_ID;

    public NotificationHandle(Context context, RemoteMessage remoteMessage) {
        this.context = context;
        createNotificationChannels();

        title = remoteMessage.getData().get("title");
        message = remoteMessage.getData().get("message");


        assignChannelWithType(type);
//        Intent intent = NotificationClickIntentHandler.getIntentByType(context, type, id);

        Intent intent = new Intent(context, MyTripActivity.class);
        showNotification(intent);
    }

    private void assignChannelWithType(String type) {

        //TODO: create channel appropiately
        CHANNEL_ID = BASIC;
    }



    private void createNotificationChannels() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel chan4 = new NotificationChannel(BASIC,
                    "Basic", NotificationManager.IMPORTANCE_HIGH);
            chan4.setLightColor(Color.BLUE);
            chan4.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            getManager().createNotificationChannel(chan4);
        }
    }

    private NotificationManager getManager() {
        if (manager == null) {
            manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return manager;
    }

    public void showNotification(Intent intent) {
        int notifyID;
        try {
            notifyID = Integer.parseInt(id);
        } catch (Exception e) {
            notifyID = 123;
        }

        Intent homePage = new Intent(context, MainActivity.class);

        //setting the notification pending intent
        PendingIntent resultPendingIntent =
                PendingIntent.getActivities(
                        context,
                        notifyID,
                        new Intent[]{homePage, intent},
                        PendingIntent.FLAG_UPDATE_CURRENT
                );


        //creating notification with icon, title
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setContentTitle(title)
                .setContentText(message)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(resultPendingIntent)
                .setAutoCancel(true);

        //setting the notification default sound
        nBuilder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);
        nBuilder.setVibrate(new long[]{100, 100, 100, 100});

        getManager().notify(notifyID, nBuilder.build());


    }

}
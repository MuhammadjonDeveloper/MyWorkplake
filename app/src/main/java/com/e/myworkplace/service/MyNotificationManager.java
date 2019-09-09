package com.e.myworkplace.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.net.Uri;
import android.view.View;
import androidx.core.app.NotificationCompat;
import com.e.myworkplace.R;
import com.e.myworkplace.activitys.PinCodeActivity;


public class MyNotificationManager {
    private static MyNotificationManager Instaince;
    private Context Ctx;

    private MyNotificationManager(Context ctx) {
        this.Ctx = ctx;
    }

    public static MyNotificationManager getInstaince(Context context) {
        if (Instaince == null) {
            Instaince = new MyNotificationManager(context);
        }
        return Instaince;
    }

    public void displyNotification(String title, String body) {
        NotificationManager notificationManager = (NotificationManager) Ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        Uri uri =Uri.parse("//android_asset/ringtone.mp3");
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            NotificationChannel notificationChannel = new NotificationChannel(Constantss.CHANAL_ID, Constantss.CHANAL_NAME,
//                    NotificationManager.IMPORTANCE_MIN);
//            notificationChannel.setDescription("EDMTDev chanel for app test FCM ");
//            notificationChannel.enableLights(true);
//            notificationChannel.setSound(uri, Notification.AUDIO_ATTRIBUTES_DEFAULT);
//            notificationChannel.setLightColor(Color.RED);
//            notificationChannel.setVibrationPattern(new long[]{200, 400, 500, 1000});
//            notificationChannel.enableVibration(true);
//
//            notificationManager.createNotificationChannel(notificationChannel);

//        }


        Intent intent = new Intent(Ctx, PinCodeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(Ctx, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(Ctx, Constantss.CHANAL_ID);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(body);
        notificationBuilder.setVibrate(new long[]{200, 400, 500, 1000});
        notificationBuilder.setSound(uri);
        notificationBuilder.setSmallIcon(R.drawable.logo_notification);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setTicker("Hearty365");
        notificationBuilder.setContentInfo("Info");
        notificationBuilder.setWhen(System.currentTimeMillis());
        notificationManager.notify(1, notificationBuilder.build());


//        NotificationCompat.Builder builder=new NotificationCompat.Builder(Ctx, Constantss.CHANAL_ID);
//        builder.setSmallIcon(R.drawable.devops);
//        builder.setContentTitle(title);
//        builder.setContentText(body);
//        builder.setSound(uri);
//        builder.setVibrate(new long[]{0,200,300,400,500,400,400});
//        Intent intent=new Intent(Ctx, PinCodeActivity.class);
//
//        PendingIntent pendingIntent=PendingIntent.getActivity(Ctx,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//        builder.setContentIntent(pendingIntent);
//        NotificationManager notificationManager= (NotificationManager) Ctx.getSystemService(Context.NOTIFICATION_SERVICE);
//        if (notificationManager!=null) {
//            notificationManager.notify(1,builder.build());
//        }
    }
}

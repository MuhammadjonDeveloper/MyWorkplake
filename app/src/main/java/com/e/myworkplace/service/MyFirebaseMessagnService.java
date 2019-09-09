package com.e.myworkplace.service;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagnService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if (remoteMessage!=null) {
            sendNotificationRemoteMessage(remoteMessage);
        }
    }

    public void sendNotificationRemoteMessage(RemoteMessage remoteMessage) {
        MyNotificationManager.getInstaince(this).displyNotification(remoteMessage.getNotification()
                .getTitle(),remoteMessage.getNotification().getBody());

    }
}

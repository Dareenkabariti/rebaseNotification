package com.example.authapp;

import android.app.Service;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService2 extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);
        getFirebaseMessage(message.getNotification().getTitle() , message.getNotification().getBody());
    }

    private void getFirebaseMessage(String title , String msg) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"myFirebaseChannel")
               // .setSmallIcon(r)
                .setContentTitle(title)
                .setContentText(msg)
                .setAutoCancel(true);


        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(101, builder.build());
    }


}

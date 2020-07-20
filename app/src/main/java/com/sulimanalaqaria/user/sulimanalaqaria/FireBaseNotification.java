package com.sulimanalaqaria.user.sulimanalaqaria;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.TaskStackBuilder;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FireBaseNotification extends FirebaseMessagingService {
    private static final String channel_1_id = "channel1";
    private static final String channel_2_id = "channel1";
    private static int count = 0;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Intent intent = new Intent(this, Notification.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ALARM)
                .build();
       /* TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(intent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);*/
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
        String channelId = "Default";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channel_1_id)
                .setSmallIcon(R.drawable.logosuliman)
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setContentText(remoteMessage.getNotification().getBody()).setAutoCancel(true)
               .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSound(sound);
        // NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
          /*  NotificationChannel channel = new NotificationChannel(channelId, "Default channel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(remoteMessage.getNotification().getBody());
            channel.setName(remoteMessage.getNotification().getTitle());
            channel.setSound(sound, audioAttributes);
            manager.createNotificationChannel(channel);*/
            NotificationChannel channel = new NotificationChannel(channel_1_id, "channel 1", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("this is firdt notify");
            NotificationManager manager1 = getSystemService(NotificationManager.class);
            manager1.createNotificationChannel(channel);
        }
        manager.notify(count, builder.build());
        count++;
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }


}

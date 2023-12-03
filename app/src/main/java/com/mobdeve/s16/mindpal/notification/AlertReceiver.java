package com.mobdeve.s16.mindpal.notification;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String Label = intent.getStringExtra("KeyLabel");
        int id = intent.getIntExtra("KeyID", 0);
        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannel1Notification("MindPal", Label);
        notificationHelper.getManager().notify(id, nb.build());
    }
}

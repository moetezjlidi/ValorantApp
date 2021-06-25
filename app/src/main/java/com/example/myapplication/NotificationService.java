package com.example.myapplication;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
public class NotificationService extends FirebaseMessagingService {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build();
        Uri sound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + getApplicationContext().getPackageName() + "/" + R.raw.match);  //Here is FILE_NAME is the name of file that you want to play
        NotificationCompat.BigPictureStyle s = new NotificationCompat.BigPictureStyle();
        s.bigPicture(BitmapFactory.decodeResource(getResources() , R.mipmap.iceboxmap_foreground));
        Notification notification = new NotificationCompat.Builder(this , "cnid")
                .setContentTitle(remoteMessage.getData().get("title"))
                .setContentText(remoteMessage.getData().get("body"))
                .setSmallIcon(R.mipmap.ic_launcher).setStyle(s).setSound(sound)
                .build();
        NotificationManagerCompat manager = NotificationManagerCompat.from(getApplicationContext());
        MediaPlayer mp= MediaPlayer.create(getApplicationContext(), R.raw.match);
        mp.start();
        manager.notify(123, notification);
        Log.d("Not" , "Done");
    }
}
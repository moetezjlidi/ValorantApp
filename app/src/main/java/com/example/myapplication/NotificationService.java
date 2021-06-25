package com.example.myapplication;

import android.app.Activity;
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

import com.example.myapplication.Login.Auth;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
public class NotificationService extends FirebaseMessagingService {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage ) {
        super.onMessageReceived(remoteMessage);
        /*AudioAttributes attributes = new AudioAttributes.Builder()
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
        Log.d("Not" , "Done");*/
        Activity currentActivity = ((MyApp)getApplicationContext().getApplicationContext()).getCurrentActivity();
        Auth  auth =  (Auth) currentActivity.getIntent().getExtras().get("auth");
        Log.d("Jaw"  , "Got auth");
        Log.d("ID ha zebi" , auth.getUid());
        if (remoteMessage.getData().get("title").equals(auth.getUid())){
            NotificationManager mNotificationManager;

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(getApplicationContext(), "Your_channel_id");
            Intent ii = new Intent(getApplicationContext(), User.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, ii, 0);

            NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
            bigText.bigText("Test");
            bigText.setBigContentTitle("Yo , Match found , You got 90 secs to pick");
            bigText.setSummaryText("Valo app");
            PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                    new Intent(this, agent_select.class).putExtra("auth" , auth), PendingIntent.FLAG_UPDATE_CURRENT);

            mBuilder.setContentIntent(contentIntent);
            mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
            mBuilder.setContentTitle("Match found");
            mBuilder.setContentText("Omek kahba ya moetez");
            mBuilder.setPriority(Notification.PRIORITY_HIGH);
            mBuilder.setStyle(bigText);

            mNotificationManager =
                    (NotificationManager) getApplicationContext().getSystemService(getApplicationContext().NOTIFICATION_SERVICE);

// === Removed some obsoletes
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            {
                String channelId = "Your_channel_id";
                NotificationChannel channel = new NotificationChannel(
                        channelId,
                        "Channel human readable title",
                        NotificationManager.IMPORTANCE_HIGH);
                mNotificationManager.createNotificationChannel(channel);
                mBuilder.setChannelId(channelId);
            }
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){ mBuilder.setChannelId("Your_channel_id"); }
            MediaPlayer mp= MediaPlayer.create(getApplicationContext(), R.raw.match);
            mp.start();
            mNotificationManager.notify(0, mBuilder.build());
        }
        else{
            Log.d("NO MATCH" , "NOOO");
        }

    }
}
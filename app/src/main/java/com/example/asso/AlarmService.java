package com.example.asso;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;

import androidx.annotation.Nullable;

public class AlarmService extends Service {
    private MediaPlayer player;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(this, Settings.System.DEFAULT_NOTIFICATION_URI);
        player.setLooping(true);
        player.start();
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        player.stop();
    }
}

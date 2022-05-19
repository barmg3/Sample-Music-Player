package com.barmge.sampalemusicplayer.services

import android.app.*
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.barmge.sampalemusicplayer.activity.MainActivity
import com.barmge.sampalemusicplayer.R
import com.barmge.sampalemusicplayer.utils.Constants.Channel_Id
import com.barmge.sampalemusicplayer.utils.Constants.Music_Notification_id

class MusicService : Service() {

    private  lateinit var musicPlayer : MediaPlayer

    override fun onBind(p0: Intent?): IBinder? {
        return  null
    }

    override fun onCreate() {
        super.onCreate()
        initMusic()
        createNotificationChannel()
        startForeground(Music_Notification_id, showNotification())
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        showNotification()
        musicPlayer.start()

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        musicPlayer.stop()
    }


    private fun createNotificationChannel(){

            val serviceChannel = NotificationChannel(
                Channel_Id, "Music Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val manager = getSystemService(
                NotificationManager::class.java
            )

            manager.createNotificationChannel(serviceChannel)
    }


    private fun showNotification() : Notification {

        val notificationIntent = Intent(this , MainActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(this , 0 , notificationIntent, FLAG_IMMUTABLE)

        val notification = Notification
            .Builder(this , Channel_Id)
            .setContentText("Music Player")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()

        return notification
    }

    private fun initMusic(){
        musicPlayer = MediaPlayer.create(this, R.raw.notyourdope)
        musicPlayer.isLooping = true
        musicPlayer.setVolume(100F , 100F)

    }
}
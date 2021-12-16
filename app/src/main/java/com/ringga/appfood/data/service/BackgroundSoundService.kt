package com.ringga.appfood.data.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.ringga.appfood.R
import com.ringga.appfood.data.service.Preferences.getMediaPosition
import com.ringga.appfood.data.service.Preferences.getMediaUrl
import com.ringga.appfood.data.service.Preferences.setMediaPosition


/**
 * Created by jorgesys on 02/02/2015.
 */
/* Add declaration of this service into the AndroidManifest.xml inside application tag*/
class BackgroundSoundService : Service() {
    lateinit var player: MediaPlayer
    override fun onBind(arg0: Intent): IBinder? {
        Log.i(TAG, "onBind()")
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.i(TAG, "onCreate()")
        player = MediaPlayer.create(this, Uri.parse(getMediaUrl(applicationContext)))
        Preferences.setMediaDurasiLagu(applicationContext, player.duration)
        setMediaPosition(applicationContext, player.currentPosition)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        player.isLooping = true // Set looping
        player.setVolume(100f, 100f)
        Log.i(TAG, "onStartCommand()")
        if (getMediaPosition(applicationContext) > 0) {
            Log.i(
                TAG,
                "onStartCommand(), position stored, continue from position : " + getMediaPosition(
                    applicationContext
                )
            )
            player.start()
            player.seekTo(getMediaPosition(applicationContext))
        } else {
            Log.i(TAG, "onStartCommand() Start!...")
            player.start()
        }
        //re-create the service if it is killed.

        setMediaPosition(applicationContext, player.currentPosition)
        return START_STICKY
    }



    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy() , service stopped! Media position: " + player.currentPosition)
        //Save current position before destruction.
        setMediaPosition(applicationContext, player.currentPosition)

        player.pause()
        player.release()
    }

    override fun onLowMemory() {
        Log.i(TAG, "onLowMemory()")
        setMediaPosition(applicationContext, player.currentPosition)
    }

    //Inside AndroidManifest.xml add android:stopWithTask="false" to the Service definition.
    override fun onTaskRemoved(rootIntent: Intent) {
        Log.i(TAG, "onTaskRemoved(), save current position: " + player.currentPosition)
        //instead of stop service, save the current position.
        //stopSelf();
        setMediaPosition(applicationContext, player.currentPosition)
    }

    companion object {
        private const val TAG = "BackgroundSoundService"
    }
}
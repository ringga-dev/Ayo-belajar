package com.ringga.appfood.data.service

import android.content.ContentValues.TAG
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.ringga.appfood.data.service.Preferences.getMediaPosition

object PlayMusic {
    private lateinit var url: String
    private var mediaPlayer: MediaPlayer?=null

    fun playAudio(r: String, context: Context) {
        url = r
        mediaPlayer = MediaPlayer()
        mediaPlayer?.setAudioStreamType(AudioManager.STREAM_MUSIC)
        mediaPlayer?.setDataSource(context, Uri.parse(url))
        Toast.makeText(context, context.toString(), Toast.LENGTH_SHORT).show()

        mediaPlayer?.isLooping = true // Set looping

        mediaPlayer?.setVolume(100f, 100f)
        Log.i(TAG, "onStartCommand()")
        if (getMediaPosition(context) > 0) {
            Log.i(
                TAG,
                "onStartCommand(), position stored, continue from position : " + getMediaPosition(
                    context
                )
            )
            mediaPlayer?.start()
            mediaPlayer?.seekTo(getMediaPosition(context))
        } else {
            Log.i(TAG, "onStartCommand() Start!...")
            mediaPlayer?.start()
        }
    }

    fun stop(context: Context){
        Preferences.setMediaPosition(context, mediaPlayer!!.currentPosition)
        mediaPlayer?.pause()
        mediaPlayer?.release()
    }

}
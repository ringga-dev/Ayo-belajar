package com.ringga.appfood.ui.home.music

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.ringga.appfood.R
import com.ringga.appfood.databinding.ActivityPlayMusicBinding
import com.ringga.appfood.databinding.CardControlBinding
import java.io.IOException
import java.util.concurrent.TimeUnit

class PlayMusicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlayMusicBinding
    private lateinit var viewModel: ListMusicViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ListMusicFragment.newInstance())
                .commitNow()
        }

        viewModel = ViewModelProvider(this).get(ListMusicViewModel::class.java)
        viewModel.getAllAudioFromDevice(this)

    }




}
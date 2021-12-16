package com.ringga.appfood.ui.home.music

import android.annotation.SuppressLint
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import com.ringga.appfood.R
import com.ringga.appfood.data.service.BackgroundSoundService
import com.ringga.appfood.data.service.Preferences.getMediaDurasiLagu
import com.ringga.appfood.data.service.Preferences.getMediaPosition
import com.ringga.appfood.databinding.CardControlBinding
import com.ringga.appfood.databinding.FragmentPlayAudioBinding
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

class PlayAudioFragment : Fragment() {
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    companion object {
        fun newInstance() = PlayAudioFragment()
    }

    private lateinit var control: CardControlBinding
    private var _binding: FragmentPlayAudioBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ListMusicViewModel

    var rotate: RotateAnimation? = null

    private var stts = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlayAudioBinding.inflate(inflater, container, false)
        val view = binding.root
        control = CardControlBinding.bind(binding.controlMusic.root)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListMusicViewModel::class.java)

        control.playPauseButton.setOnClickListener {

            val myService = Intent(requireContext(), BackgroundSoundService::class.java)

            if (!stts) {
                rotate = RotateAnimation(
                    0F, 360F,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f
                )
                rotate!!.duration = 10000
                rotate!!.interpolator = LinearInterpolator()
                rotate!!.repeatCount = Animation.INFINITE
                stts = true
                binding.imgCover.startAnimation(rotate)
                requireContext().startService(myService)
                control.playPauseButton.setImageResource(R.drawable.ic_pause)

                mHandler = Handler()
                mRunnable = Runnable {

                    val getCurrent = getMediaPosition(requireContext())
                    val mCurrentPosition = getCurrent / getMediaDurasiLagu(requireContext())*100
                    val duration = getMediaDurasiLagu(requireContext())
                    @SuppressLint("DefaultLocale") val time = String.format(
                        "%02d:%02d sec",
                        TimeUnit.MILLISECONDS.toMinutes(duration.toLong()),
                        TimeUnit.MILLISECONDS.toSeconds(duration.toLong()) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration.toLong()))
                    )
//                    Toast.makeText(requireContext(), "$mCurrentPosition %", Toast.LENGTH_SHORT).show()
//                    control.progressSlider.progress = mCurrentPosition
//                    control.songTotalTime.text = time
//                    control.songCurrentProgress.text = mCurrentPosition.toString()
                    // Schedule the task to repeat after 1 second



                    mHandler.postDelayed(
                        mRunnable, // Runnable
                        1000 // Delay in milliseconds
                    )
                }
                mHandler.postDelayed(
                    mRunnable, // Runnable
                    1000 // Delay in milliseconds
                )
            } else {
                stts = false
                rotate!!.cancel()
                requireContext().stopService(myService)
                control.playPauseButton.setImageResource(R.drawable.ic_play)
            }
        }
    }
}
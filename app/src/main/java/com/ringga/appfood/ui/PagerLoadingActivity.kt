package com.ringga.appfood.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ringga.appfood.MainActivity
import com.ringga.appfood.data.adapter.PagerLoadingAdapter
import com.ringga.appfood.databinding.ActivityPagerLoadingBinding
import com.ringga.appfood.ui.home.music.PlayMusicActivity

class PagerLoadingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPagerLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPagerLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        play musik
//        startService(Intent(this, MusicManager::class.java))


        val words = arrayListOf("One", "Two", "Three", "Four", "Five","next")
        val pager = binding.pager
        val tabLayout = binding.tabLayout

        pager.adapter = PagerLoadingAdapter(this, words)
//        pager.orientation = ViewPager2.ORIENTATION_VERTICAL
        TabLayoutMediator(tabLayout, pager) {tab, position ->

            if (words[position]== "next"){
                tab.text = "next"
            }
        }.attach()

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
//                Toast.makeText(this@PagerLoadingActivity, "Tab ${tab?.text} selected", Toast.LENGTH_SHORT).show()
               cek_stts(tab?.text)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                Toast.makeText(this@PagerLoadingActivity, "Tab ${tab?.text} unselected", Toast.LENGTH_SHORT).show()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
//                Toast.makeText(this@PagerLoadingActivity, "Tab ${tab?.text} reselected", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun cek_stts(txt: CharSequence?) {
        if (txt== "next"){
            Toast.makeText(this, "oke", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, PlayMusicActivity::class.java)
            startActivity(intent)
            finish()
        }else{
//            Toast.makeText(this@PagerLoadingActivity, "Tab ${txt} selected", Toast.LENGTH_SHORT).show()
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        stopService(Intent(this, MusicManager::class.java))
//    }
}
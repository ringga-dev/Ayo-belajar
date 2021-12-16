package com.ringga.appfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import com.ringga.appfood.ui.PagerLoadingActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed(Runnable {
            val intent = Intent(this, PagerLoadingActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }
}
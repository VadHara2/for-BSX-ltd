package com.vadhara7.posts.ui.splash

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.vadhara7.posts.R
import com.vadhara7.posts.databinding.ActivitySplashBinding
import com.vadhara7.posts.ui.postslist.PostActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this@SplashActivity, PostActivity::class.java)
            startActivity(intent)
            finish()
        }, 2500)

    }
}
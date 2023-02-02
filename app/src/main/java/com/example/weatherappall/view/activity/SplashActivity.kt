package com.example.weatherappall.view.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherappall.R
import com.example.weatherappall.databinding.ActivitySpashBinding
import com.example.weatherappall.view.Constants.SPLASH_TIME1
import com.example.weatherappall.view.Constants.SPLASH_TIME2
import com.example.weatherappall.view.MainActivity

class SplashActivity : AppCompatActivity() {
    private lateinit var bindActivitySpashBinding: ActivitySpashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindActivitySpashBinding = ActivitySpashBinding.inflate(layoutInflater)
        setContentView(bindActivitySpashBinding.root)
        val slideAnim = AnimationUtils.loadAnimation(this, R.anim.slide)
        bindActivitySpashBinding.sun.apply {
            startAnimation(slideAnim)
        }

        Handler().postDelayed({
            val rotateAnim = AnimationUtils.loadAnimation(this, R.anim.rotate)
            bindActivitySpashBinding.sun.startAnimation(rotateAnim)
        }, SPLASH_TIME1)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME2)
    }
}
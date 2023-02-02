package com.example.weatherappall.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import com.example.weatherappall.R
import com.example.weatherappall.databinding.SplashDeliveryBinding
import com.example.weatherappall.view.Constants
import com.example.weatherappall.view.MainActivity

class DeliverySplashActivity : AppCompatActivity() {
    private lateinit var bindDeliverySplashActivity: SplashDeliveryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindDeliverySplashActivity = SplashDeliveryBinding.inflate(layoutInflater)
        setContentView(bindDeliverySplashActivity.root)

        val slideAnim = AnimationUtils.loadAnimation(this, R.anim.translate)
        bindDeliverySplashActivity.bike.apply {
            startAnimation(slideAnim)
        }

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, Constants.SPLASH_TIME1)
    }
}
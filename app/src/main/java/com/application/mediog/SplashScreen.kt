package com.application.mediog

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.application.mediog.databinding.ActivitySplashScreenBinding
import com.application.mediog.onboarding.OnBoardingOne
import com.application.mediog.utils.SharedPrefs

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    private lateinit var binding : ActivitySplashScreenBinding
    private lateinit var handler : Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN


        handler = Handler()
        val prefs = SharedPrefs()
        val isOnBoardingShowed = prefs.getOnBoardingStatus(this@SplashScreen)
        val login = prefs.getLoginStatus(this@SplashScreen)

        if (isOnBoardingShowed){
            if (login){
                handler.postDelayed({
                    val intent = Intent(this, MainActivity::class.java)
                    val animationBundle = ActivityOptions.makeCustomAnimation(
                        this,
                        R.anim.slide_in_right,  // Enter animation
                        R.anim.slide_out_left  // Exit animation
                    ).toBundle()
                    startActivity(intent, animationBundle)
                    finish()
                },2000)
            }
            else {
                handler.postDelayed({
                    val intent = Intent(this, LoginActivity::class.java)
                    val animationBundle = ActivityOptions.makeCustomAnimation(
                        this,
                        R.anim.slide_in_right,  // Enter animation
                        R.anim.slide_out_left  // Exit animation
                    ).toBundle()
                    startActivity(intent, animationBundle)
                    finish()
                },2000)
            }
        }

        else {
            handler.postDelayed({
                val intent = Intent(this, OnBoardingOne::class.java)
                val animationBundle = ActivityOptions.makeCustomAnimation(
                    this,
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
                ).toBundle()
                startActivity(intent, animationBundle)
                finish()
            },2000)
        }

    }
}
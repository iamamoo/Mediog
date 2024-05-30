package com.application.mediog.onboarding

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.mediog.LoginActivity
import com.application.mediog.R
import com.application.mediog.databinding.ActivityOnBoardingTwoBinding
import com.application.mediog.utils.SharedPrefs

class OnBoardingTwo : AppCompatActivity() {

    private lateinit var binding : ActivityOnBoardingTwoBinding
    private val prefs = SharedPrefs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView3.setOnClickListener {
            prefs.saveOnBoarding(this@OnBoardingTwo,true)
            val intent = Intent(this, LoginActivity::class.java)
            val animationBundle = ActivityOptions.makeCustomAnimation(
                this,
                R.anim.slide_in_right,  // Enter animation
                R.anim.slide_out_left  // Exit animation
            ).toBundle()
            startActivity(intent, animationBundle)
            finishAffinity()
        }


        binding.constraintLayout.setOnClickListener {
            val intent = Intent(this, OnBoardingThree::class.java)
            val animationBundle = ActivityOptions.makeCustomAnimation(
                this,
                R.anim.slide_in_right,  // Enter animation
                R.anim.slide_out_left  // Exit animation
            ).toBundle()
            startActivity(intent, animationBundle)
        }





    }
}
package com.application.mediog

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.mediog.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.login.setOnClickListener {
            val intent = Intent(this, SigninActivity::class.java)
            val animationBundle = ActivityOptions.makeCustomAnimation(
                this,
                R.anim.slide_in_right,  // Enter animation
                R.anim.slide_out_left  // Exit animation
            ).toBundle()
            startActivity(intent, animationBundle)
            finish()
        }

        binding.signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            val animationBundle = ActivityOptions.makeCustomAnimation(
                this,
                R.anim.slide_in_right,  // Enter animation
                R.anim.slide_out_left  // Exit animation
            ).toBundle()
            startActivity(intent, animationBundle)
            finish()
        }


    }
}
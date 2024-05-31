package com.application.mediog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.mediog.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

    private lateinit var binding : ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imageView13.setOnClickListener {
            finish()
        }

        val data = intent.extras
        val name = data!!.getString("name")
        binding.textView21.text = name


    }
}
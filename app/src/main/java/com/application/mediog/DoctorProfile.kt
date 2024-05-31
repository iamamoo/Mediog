package com.application.mediog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.mediog.databinding.ActivityDoctorProfileBinding

class DoctorProfile : AppCompatActivity() {

    private lateinit var binding : ActivityDoctorProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView13.setOnClickListener {
            finish()
        }

        val data = intent.extras
        val name = data!!.getString("name")
        val special = data.getString("spacial")
        val rating = data.getFloat("rating")

        binding.textView24.text = name
        binding.textView26.text = special
        binding.rating.text = rating.toString()

        binding.imageView18.setOnClickListener {
            val intent = Intent(this@DoctorProfile,ChatActivity::class.java)
            intent.putExtra("name",name)
            startActivity(intent)
        }

        binding.bookAppoinment.setOnClickListener {
            startActivity(Intent(this@DoctorProfile,BookAppoinment::class.java))
        }


    }
}
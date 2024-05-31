package com.application.mediog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.application.mediog.databinding.ActivityMainBinding
import com.application.mediog.ui.AppoinmentFragment
import com.application.mediog.ui.ChatFragment
import com.application.mediog.ui.HomeFragment
import com.application.mediog.ui.OtherFragment
import com.application.mediog.ui.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var home : Fragment
    private val fragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        home = HomeFragment()
        fragmentManager.beginTransaction().replace(R.id.frameLayout,home).commit()


        binding.navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val home = HomeFragment()
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, home).commit()
                }
                R.id.appoinment -> {
                    val recording = AppoinmentFragment()
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, recording).commit()
                }
                R.id.other -> {
                    val recording = OtherFragment()
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, recording).commit()
                }
                R.id.chat -> {
                    val recording = ChatFragment()
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, recording).commit()
                }
                R.id.profile -> {
                    val recording = ProfileFragment()
                    fragmentManager.beginTransaction().replace(R.id.frameLayout, recording).commit()
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
            true
        }

    }
}
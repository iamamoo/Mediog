package com.application.mediog

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.application.mediog.databinding.ActivityForgetPasswordBinding
import com.application.mediog.utils.DialogsClass
import com.google.firebase.auth.FirebaseAuth

class ForgetPassword : AppCompatActivity() {
    
    
    private lateinit var dialog : DialogsClass

    private lateinit var binding : ActivityForgetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog = DialogsClass(this@ForgetPassword)

        binding.imageView9.setOnClickListener {
            finish()
        }
        
        binding.resetPassword.setOnClickListener { 
            val email = binding.emailAddress.text.toString().trim()
            dialog.showLoading()
            if (email.isNotEmpty()){
                sendPasswordResetEmail(email)
            }else {
                dialog.stopLoading()
                dialog.showDialog("Please enter your registered email first",this@ForgetPassword)
            }
        }
    }

    private fun sendPasswordResetEmail(email: String) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Password reset email sent successfully
                    dialog.stopLoading()
                    Toast.makeText(this,"Password reset email sent successfully",Toast.LENGTH_SHORT).show()

                    val intent = Intent(this, LoginActivity::class.java)
                    val animationBundle = ActivityOptions.makeCustomAnimation(
                        this,
                        R.anim.slide_in_right,  // Enter animation
                        R.anim.slide_out_left  // Exit animation
                    ).toBundle()
                    startActivity(intent, animationBundle)
                    finishAffinity()
                } else {
                    // Password reset failed
                    dialog.stopLoading()
                    dialog.showDialog("An error occurred!",this)
                }
            }
    }

}
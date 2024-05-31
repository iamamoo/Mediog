package com.application.mediog

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.application.mediog.databinding.ActivitySigninBinding
import com.application.mediog.utils.DialogsClass
import com.application.mediog.utils.SharedPrefs
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth

class SigninActivity : AppCompatActivity() {

    private lateinit var dialog : DialogsClass
    private lateinit var binding : ActivitySigninBinding

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog = DialogsClass(this@SigninActivity)


        binding.pass.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                binding.password.hint = "" // Hide the hint label
                binding.password.passwordVisibilityToggleDrawable = ContextCompat.getDrawable(this,R.drawable.ic_eye_slash_blue)

                // Tint the start icon
                val colorStateList = ContextCompat.getColorStateList(this, R.color.primary)
                binding.password.setStartIconTintList(colorStateList)

            } else {
                if (binding.pass.text.isNullOrEmpty()) {
                    binding.password.hint =
                        "Enter your password" // Show the hint label if the field is empty

                    binding.password.passwordVisibilityToggleDrawable = ContextCompat.getDrawable(this,R.drawable.ic_eye_slash)

                    // Tint the start icon
                    val colorStateList = ContextCompat.getColorStateList(this, R.color.hint)
                    binding.password.setStartIconTintList(colorStateList)
                }
            }
        }

        binding.loginWithGoogle.setOnClickListener {
                dialog.showDialog("This functionality is not implemented yet.",this)
        }

        binding.login.setOnClickListener {
            dialog.showLoading()
            if (binding.emailAddress.text.toString().isNotEmpty() && binding.pass.text!!.isNotEmpty()) {
                val email = binding.emailAddress.text.toString().trim()
                val pass = binding.pass.text.toString().trim()
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass)
                    .addOnSuccessListener {
                        dialog.stopLoading()
                        showCustomDialog()
                    }.addOnFailureListener {
                      dialog.stopLoading()
                      dialog.showDialog(it.localizedMessage!!.toString(),this@SigninActivity)
                    }

            } else {
                Toast.makeText(
                    this@SigninActivity,
                    "Please fill all the details",
                    Toast.LENGTH_SHORT
                ).show()
                dialog.stopLoading()
            }
        }

        binding.textView10.setOnClickListener {
            startActivity(Intent(this@SigninActivity, SignupActivity::class.java))
        }

        binding.textView7.setOnClickListener {
            startActivity(Intent(this@SigninActivity, ForgetPassword::class.java))
        }


    }

    private fun showCustomDialog() {
        // Inflate the custom layout
        val dialogView = Dialog(this@SigninActivity)
        dialogView.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialogView.setContentView(R.layout.login_dialog)

        // Create the dialog
        dialogView.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dialogButton: MaterialButton = dialogView.findViewById(R.id.goToHome)

        // Set click listener for the dialog button
        dialogButton.setOnClickListener {
            dialogView.dismiss()
            val prefs = SharedPrefs()
            prefs.saveLoginStatus(this@SigninActivity,true)
            startActivity(Intent(this@SigninActivity, MainActivity::class.java))
            finishAffinity()
        }

        dialogView.setCancelable(false)

        // Show the dialog
        dialogView.show()
    }
}
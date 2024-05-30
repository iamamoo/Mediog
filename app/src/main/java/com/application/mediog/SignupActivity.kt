package com.application.mediog

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.application.mediog.R.*
import com.application.mediog.databinding.ActivitySignupBinding
import com.application.mediog.utils.DialogsClass
import com.application.mediog.utils.SharedPrefs
import com.google.android.material.button.MaterialButton
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class SignupActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySignupBinding
    private lateinit var dialogs : DialogsClass
    private var prefs = SharedPrefs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialogs = DialogsClass(this@SignupActivity)
        changeCheckBoxColor()

        binding.pass.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {

                binding.password.hint = "" // Hide the hint label
                binding.password.passwordVisibilityToggleDrawable = ContextCompat.getDrawable(this,
                    drawable.ic_eye_slash_blue)

                // Tint the start icon
                val colorStateList = ContextCompat.getColorStateList(this, color.primary)
                binding.password.setStartIconTintList(colorStateList)

            } else {
                if (binding.pass.text.isNullOrEmpty()) {
                    binding.password.hint =
                        "Enter your password" // Show the hint label if the field is empty

                    binding.password.passwordVisibilityToggleDrawable = ContextCompat.getDrawable(this,
                        drawable.ic_eye_slash)

                    // Tint the start icon
                    val colorStateList = ContextCompat.getColorStateList(this, color.hint)
                    binding.password.setStartIconTintList(colorStateList)
                }
            }
        }


        binding.login.setOnClickListener {
            dialogs.showLoading()
            val name = binding.name.text.toString().trim()
            val email = binding.emailAddress.text.toString().trim()
            val password = binding.pass.text.toString().trim()
            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()){

                if (binding.checkBox.isChecked){
                    if(binding.pass.text.toString().trim().length < 6){
                        dialogs.stopLoading()
                        binding.password.setBackgroundResource(drawable.mail_bg_error)
                        dialogs.showDialog("Password should be 6 character long",this@SignupActivity)
                    } else {
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                            storeData(name,email, password)
                        }.addOnFailureListener {
                            dialogs.stopLoading()
                            dialogs.showDialog(it.message.toString(),this@SignupActivity)
                        }
                    }

                } else {
                    dialogs.stopLoading()
                    dialogs.showDialog("Please agree to our policies",this@SignupActivity)
                }


            } else {
                dialogs.stopLoading()
                dialogs.showDialog("Please fill all the fields",this@SignupActivity)
            }
        }

        binding.textView10.setOnClickListener {
            finish()
        }

        binding.imageView6.setOnClickListener {
            finish()
        }

    }

    private fun storeData(name : String,email:String,password:String) {
        val db = Firebase.firestore
        val data = hashMapOf(
            "name" to name,
            "email" to email,
            "password" to password,
            "role" to "patient",
            "uuid" to FirebaseAuth.getInstance().currentUser!!.uid,
        )
        db.collection("users").document(FirebaseAuth.getInstance().currentUser!!.uid).set(data)
            .addOnSuccessListener {
                dialogs.stopLoading()
                goToHome()
            }.addOnFailureListener {
                dialogs.stopLoading()
                it.localizedMessage?.let { it1 -> dialogs.showDialog(it1,this@SignupActivity) }
            }
    }

    private fun changeCheckBoxColor() {


        val spannableString = SpannableString(binding.checkBox.text.toString().trim())

        val startIndex = 23 // Replace with the starting index of your bold text
        val endIndex = 39   // Replace with the ending index of your bold text

        // Apply a StyleSpan to make the text bold
        val styleSpan = StyleSpan(Typeface.NORMAL)
        spannableString.setSpan(styleSpan, startIndex, endIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        // Apply a ForegroundColorSpan to change the color of the bold text
        val colorSpan = ForegroundColorSpan(resources.getColor(color.spannablecolor)) // Replace Color.RED with your desired color
        spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE)


        val startIndex2 = 44 // Replace with the starting index of your bold text
        val endIndex2 = 58   // Replace with the ending index of your bold text

        // Apply a StyleSpan to make the text bold
        val styleSpan2 = StyleSpan(Typeface.NORMAL)
        spannableString.setSpan(styleSpan2, startIndex2, endIndex2, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        // Apply a ForegroundColorSpan to change the color of the bold text
        val colorSpan2 = ForegroundColorSpan(resources.getColor(color.spannablecolor)) // Replace Color.RED with your desired color
        spannableString.setSpan(colorSpan2, startIndex2, endIndex2, Spannable.SPAN_INCLUSIVE_INCLUSIVE)



        // Set the modified SpannableString back to the TextView
        binding.checkBox.text = spannableString
    }

    private fun goToHome() {
        // Inflate the custom layout
        val dialogView = Dialog(this@SignupActivity)
        dialogView.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialogView.setContentView(layout.signup_dialog)

        // Create the dialog
        dialogView.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dialogButton: MaterialButton = dialogView.findViewById(id.goToHome)

        // Set click listener for the dialog button
        dialogButton.setOnClickListener {
            dialogView.dismiss()
            startActivity(Intent(this@SignupActivity,LoginActivity::class.java))
            finishAffinity()
        }

        dialogView.setCancelable(false)

        // Show the dialog
        dialogView.show()
    }
}
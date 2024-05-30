package com.application.mediog

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.application.mediog.databinding.ActivityNewPasswordBinding
import com.application.mediog.utils.DialogsClass
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class NewPassword : AppCompatActivity() {

    private lateinit var binding : ActivityNewPasswordBinding
    private lateinit var auth:FirebaseAuth
    private lateinit var dialogsClass : DialogsClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        dialogsClass = DialogsClass(this)

        binding.createPassword.setOnClickListener {
            dialogsClass.showLoading()
            val password = binding.pass.text.toString().trim()
            val confirmPassword = binding.confirmPass.text.toString().trim()

            if (password.isNotEmpty() && confirmPassword.isNotEmpty()){

                if (password == confirmPassword){
                    if (password.length >= 6){
                        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
                        if (user!=null){
                            user.updatePassword(password).addOnSuccessListener {
                                dialogsClass.stopLoading()
                                showCustomDialog()
                            }.addOnFailureListener {
                                dialogsClass.stopLoading()
                                dialogsClass.showDialog(it.localizedMessage!!.toString(),this)
                            }
                        }else {
                            dialogsClass.stopLoading()
                            dialogsClass.showDialog("There's a problem with your account",this)
                        }
                    }else {
                        dialogsClass.stopLoading()
                        dialogsClass.showDialog("Your password length should be 6 character long",this)
                    }
                } else {
                    dialogsClass.stopLoading()
                    dialogsClass.showDialog("Your passwords are not matched. Try again",this)
                }

            } else {
                dialogsClass.stopLoading()
                dialogsClass.showDialog("Please fill all the details",this)
            }

        }


    }

    private fun showCustomDialog() {
        // Inflate the custom layout
        val dialogView = Dialog(this)
        dialogView.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialogView.setContentView(R.layout.password_changed_dialog)

        // Create the dialog
        dialogView.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dialogButton: MaterialButton = dialogView.findViewById(R.id.goToHome)

        // Set click listener for the dialog button
        dialogButton.setOnClickListener {
            dialogView.dismiss()
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }

        dialogView.setCancelable(false)

        // Show the dialog
        dialogView.show()
    }
}
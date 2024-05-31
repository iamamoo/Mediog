package com.application.mediog

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.application.mediog.databinding.ActivityBookAppoinmentBinding
import com.application.mediog.screens.ConsultationActivity
import com.application.mediog.utils.DialogsClass
import com.google.android.material.button.MaterialButton

class BookAppoinment : AppCompatActivity() {

    private lateinit var binding : ActivityBookAppoinmentBinding
    private lateinit var dialogsClass: DialogsClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookAppoinmentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialogsClass = DialogsClass(this@BookAppoinment)

        binding.bookAppoinment.setOnClickListener {
            dialogsClass.showLoading()
            val symptoms = binding.emailAddress.text.toString().trim()
            val disease = binding.disease.text.toString().trim()
            val sleepChanges = binding.sleepChanges.text.toString().trim()
            val frequency = binding.symptoms.text.toString().trim()
            val medications = binding.medications.text.toString().trim()
            val allergies = binding.allergies.text.toString().trim()
            val diet = binding.diet.text.toString().trim()

            if (symptoms.isNotEmpty() && disease.isNotEmpty() && sleepChanges.isNotEmpty() && frequency.isNotEmpty() && medications.isNotEmpty() &&
                allergies.isNotEmpty() && diet.isNotEmpty()){

                scheduleMeeting()

            }else {
                dialogsClass.stopLoading()
                dialogsClass.showDialog("Please fill all the details",this@BookAppoinment)
            }


        }




    }

    private fun scheduleMeeting() {
        // Inflate the custom layout
        val dialogView = Dialog(this)
        dialogView.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialogView.setContentView(R.layout.schedule_meeting)

        // Create the dialog
        dialogView.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val dialogButton: MaterialButton = dialogView.findViewById(R.id.goToHome)

        // Set click listener for the dialog button
        dialogButton.setOnClickListener {
            startActivity(Intent(this@BookAppoinment,ConsultationActivity::class.java))
            finishAffinity()
        }

        dialogView.setCancelable(false)

        // Show the dialog
        dialogView.show()
    }

}
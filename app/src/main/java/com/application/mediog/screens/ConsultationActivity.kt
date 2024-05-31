package com.application.mediog.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.application.mediog.DoctorProfile
import com.application.mediog.R
import com.application.mediog.adapters.DoctorsAdapter
import com.application.mediog.databinding.ActivityConsultationBinding
import com.application.mediog.models.DoctorModel
import com.application.mediog.utils.OnItemClickListener
import java.util.Locale

class ConsultationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityConsultationBinding
    private lateinit var list : ArrayList<DoctorModel>
    private val doctorFilteredListlist = ArrayList<DoctorModel>()
    private lateinit var adapter: DoctorsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConsultationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView13.setOnClickListener {
            finish()
        }

        binding.searchView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // Filter the items when text changes
                filterDoctor(s.toString())
                if (binding.searchView.text.toString().trim().isEmpty()){

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // No implementation needed
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // No implementation needed
            }
        })

        list = ArrayList()
        list.add(DoctorModel("Dr. Yerdana Mukanbay","Specialist Cardiologist",4.5f,R.drawable.doc_sample_img,200,4))
        list.add(DoctorModel("Dr. Daulet Zhaksylyk","Specialist Dentist",3.5f,R.drawable.doc_sample_img,20,3))
        list.add(DoctorModel("Dr. Johan Smith","Specialist Cardiologist",5f,R.drawable.doc_sample_img,500,5))


        adapter = DoctorsAdapter(list,binding.constraintLayout2,object : OnItemClickListener{
            override fun onItemClick(position: Int) {
                val item = list[position]
                val name = item.doctorName
                val spacial = item.specialist.toString()
                val rating = item.rating
                val img = item.img
                val review = item.reviews
                val star = item.star

                val intent = Intent(this@ConsultationActivity,DoctorProfile::class.java)
                intent.putExtra("name",name)
                intent.putExtra("spacial",spacial)
                intent.putExtra("rating",rating)
                intent.putExtra("img",img)
                intent.putExtra("review",review)
                intent.putExtra("star",star)
                startActivity(intent)
            }

        })

        binding.constraintLayout2.visibility = View.GONE
        binding.doctorRec.adapter = adapter


    }

    private fun filterDoctor(text: String) {
        doctorFilteredListlist.clear()
        for (item in list) {
            if (item.doctorName!!.lowercase(Locale.getDefault()).contains(text.toLowerCase(
                    Locale.ROOT))) {
                doctorFilteredListlist.add(item)
            }
        }
        adapter.updateList(doctorFilteredListlist)
    }
}
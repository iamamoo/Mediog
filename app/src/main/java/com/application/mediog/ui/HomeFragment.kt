package com.application.mediog.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.application.mediog.databinding.FragmentHomeBinding
import com.application.mediog.screens.ConsultationActivity

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.consultation.setOnClickListener {
            startActivity(Intent(requireContext(),ConsultationActivity::class.java))
        }



        return binding.root
    }


}
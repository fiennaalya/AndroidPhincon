package com.example.androidapp.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidapp.R
import com.example.androidapp.databinding.FragmentContentBoardingBinding

class ContentBoardingFragment : Fragment() {
    private lateinit var binding: FragmentContentBoardingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val image = binding.image1
        val index = requireArguments().getInt(ARG_SECTION_NUMBER, 0)

        when (index) {
            1 -> image.setImageResource(R.drawable.toko_phincon)
            2 -> image.setImageResource(R.drawable.toko_phincon2)
            else -> image.setImageResource(R.drawable.toko_phincon3)
        }
    }

    companion object{
        const val ARG_SECTION_NUMBER = "section_number"
    }

}
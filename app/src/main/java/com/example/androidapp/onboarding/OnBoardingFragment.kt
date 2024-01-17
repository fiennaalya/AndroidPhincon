package com.example.androidapp.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.androidapp.databinding.FragmentOnBoardingBinding
import com.example.androidapp.login.DaftarFragment
import com.example.androidapp.login.MasukFragment
import com.google.android.material.tabs.TabLayoutMediator

class OnBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    private var currentPage = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val  viewPager = binding.viewPager
        val gabungButton = binding.gabungButton
        val nextButton = binding.next
        val skipButton = binding.skip

        viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, viewPager) { _, _ -> }.attach()
        nextButton.setOnClickListener {
            if (currentPage < sectionsPagerAdapter.itemCount - 1) {
                currentPage++
            } else {
                currentPage = 0
            }
            viewPager.setCurrentItem(currentPage, true)
        }

        gabungButton.setOnClickListener {
            with(binding) {
                val daftarFragment = DaftarFragment()
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(onboarding.id, daftarFragment)
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

        skipButton.setOnClickListener{
            with(binding){
                val masukFragment = MasukFragment()
                val transaction: FragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
                transaction.replace(onboarding.id, masukFragment)
                transaction.addToBackStack(null)
                transaction.commit()

            }
        }

    }

}
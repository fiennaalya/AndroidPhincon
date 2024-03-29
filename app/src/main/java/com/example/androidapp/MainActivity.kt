package com.example.androidapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.androidapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val splashFragment = SplashFragment()
        navigateFragment(splashFragment)
    }

    fun navigateFragment(fragment: Fragment) = with(binding) {
        supportFragmentManager.beginTransaction()
            .replace(main.id, fragment, fragment.tag)
            .commit()
    }
}

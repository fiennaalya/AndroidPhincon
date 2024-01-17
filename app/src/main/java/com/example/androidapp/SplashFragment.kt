package com.example.androidapp

import android.animation.ValueAnimator
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.androidapp.databinding.FragmentSplashBinding
import com.example.androidapp.login.MasukFragment
import com.example.androidapp.onboarding.OnBoardingFragment

class SplashFragment : Fragment() {
    private lateinit var splashBinding : FragmentSplashBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        splashBinding = FragmentSplashBinding.inflate(inflater, container, false)
        return splashBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

        splashBinding.run {
            animateView(this.constraintSplash, FADE_ANIMATION, 0f, 1f, 1000L)
            animateView(this.splashLogo, ALPHA_ANIMATION, (0.5).toFloat(), 1f, 800L)
            animateView(this.splashYellow, TRANSLATION_ANIMATION, 0f, -55f, 1000L)
            animateView(this.splashYellow, ROTATE_ANIMATION, 0f, -20f, 1000L)
            animateView(this.splashRed, TRANSLATION_ANIMATION, 0f, 75f, 1000L)
            animateView(this.splashRed, ROTATE_ANIMATION, 0f, 20f, 1000L)
            animateView(this.splashGreen, "margin_anim", 0f, 150f, 1000L)
        }

        if(ShowOnboarding()){
            Handler(Looper.getMainLooper()).postDelayed({
                saveOnboardingFlag()
                (activity as MainActivity).navigateFragment(OnBoardingFragment())
            },1500L)
        }else{
            Handler(Looper.getMainLooper()).postDelayed({
                (activity as MainActivity).navigateFragment(MasukFragment())
            },1500L)
        }
    }

    private fun ShowOnboarding(): Boolean {
        return !sharedPreferences.getBoolean("onboarding_shown", false)
    }

    private fun saveOnboardingFlag() {
        sharedPreferences.edit().putBoolean("onboarding_shown", true).apply()
    }

    private fun animateView(view:View, propertyName: String, startValue: Float, endValue:Float,durationAnimation: Long) {
        val animator = ValueAnimator.ofFloat(startValue,endValue)
        animator.duration = durationAnimation
        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            view.run {
                when {
                    propertyName.equals(ROTATE_ANIMATION, true) -> {
                        rotation=animatedValue
                    }

                    propertyName.equals(TRANSLATION_ANIMATION, true) -> {
                        translationX=animatedValue
                    }

                    propertyName.equals(ALPHA_ANIMATION, true) -> {
                        scaleX=animatedValue
                    }

                    propertyName.equals(FADE_ANIMATION, true) -> {
                        alpha=animatedValue
                    }

                    else -> {
                        val lp = layoutParams as ConstraintLayout.LayoutParams
                        lp.setMargins(0,0,0, animatedValue.toInt())
                        layoutParams = lp
                        requestLayout()
                    }
                }
            }
        }
        animator.start()
    }

    companion object{
        const val ALPHA_ANIMATION = "alpha_animation_key"
        const val ROTATE_ANIMATION = "rotate_animation_key"
        const val TRANSLATION_ANIMATION = "translation_animation_key"
        const val FADE_ANIMATION = "fade_animation_key"
    }

}
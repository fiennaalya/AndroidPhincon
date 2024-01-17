package com.example.androidapp.login

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.androidapp.databinding.FragmentDaftarBinding

class DaftarFragment : Fragment() {
    private lateinit var binding : FragmentDaftarBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDaftarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() = with(binding) {
        formEmail.editText?.doOnTextChanged { text, start, before, count ->
            emailValidate()
        }

        formPassword.editText?.doOnTextChanged { text, start, before, count ->
            passwordValidate()
        }
    }

    private fun emailValidate() {
        val emailText = binding.formEmail
        val emailEditText = emailText.editText
        val email = emailEditText?.text.toString().trim()
        val emailPattern = Patterns.EMAIL_ADDRESS

        if(emailPattern.matcher(email).matches()){
            emailText.error = null
            emailText.helperText = "Contoh: email@gmail.com"
        } else{
            emailText.error = "Email tidak valid"
            emailText.helperText = null
        }
    }

    private fun passwordValidate() {
        val passwordText = binding.formPassword
        val passwordEditText = passwordText.editText
        val password = passwordEditText?.text.toString().trim()

        if((password.length >= 8)== true){
            passwordText.error = null
            passwordText.helperText = "Minimal 8 karakter"
        }else{
            passwordText.error = "Password tidak valid"
            passwordText.helperText = null
        }
    }
}
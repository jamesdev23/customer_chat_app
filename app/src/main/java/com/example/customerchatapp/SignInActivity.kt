package com.example.customerchatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.customerchatapp.databinding.ActivityMainBinding
import com.example.customerchatapp.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
package com.pangondionkn.wingssale.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pangondionkn.wingssale.R
import com.pangondionkn.wingssale.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val TAG = LoginActivity::class.java.simpleName
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
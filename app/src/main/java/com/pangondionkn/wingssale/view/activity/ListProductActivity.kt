package com.pangondionkn.wingssale.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pangondionkn.wingssale.databinding.ActivityListProductBinding

class ListProductActivity : AppCompatActivity() {
    private val TAG = ListProductActivity::class.java.simpleName
    private lateinit var binding: ActivityListProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
package com.pangondionkn.wingssale.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pangondionkn.wingssale.databinding.ActivityListProductBinding

class ListProductActivity : AppCompatActivity() {
    private val TAG = ListProductActivity::class.java.simpleName
    private lateinit var binding: ActivityListProductBinding

    companion object{
        const val EXTRA_USER = "EXTRA_USER"
        fun newIntent(context: Context, deliveredUser: String): Intent = Intent(context, ListProductActivity::class.java)
            .putExtra(EXTRA_USER, deliveredUser)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
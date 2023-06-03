package com.example.cancerwise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cancerwise.databinding.ActivityDetailResultBinding
import com.example.cancerwise.databinding.ActivityStartBinding

class DetailResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }
}
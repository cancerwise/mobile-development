package com.example.cancerwise.ui.quizioner

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.example.cancerwise.data.Resource
import com.example.cancerwise.ui.MainActivity
import com.example.cancerwise.data.ViewModelFactory
import com.example.cancerwise.databinding.ActivityResultBinding
import com.google.firebase.auth.FirebaseAuth
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    private val viewModel: QuizionerViewModel by viewModels {
        ViewModelFactory(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpData()

        binding.btnTakequiz.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun setUpData() {
        val auth = FirebaseAuth.getInstance()

        binding.tvResultId.text = "will be added soon"
        binding.tvResultAge.text = "will be added soon"
        binding.tvResultGender.text = "will be added soon"
        binding.tvResultName.text = auth.currentUser?.displayName
        binding.tvResult.text = intent.getStringExtra(EXTRA_RESULT)
        binding.tvResultTittle.text = intent.getStringExtra(EXTRA_TITLE)
        binding.tvResultAction.text = intent.getStringExtra(EXTRA_ACTION )

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val current = LocalDateTime.now().format(formatter)

        binding.tvDate.text = current
    }

    companion object {
        const val EXTRA_RESULT = "extra_result"
        const val EXTRA_ACTION = "extra_action"
        const val EXTRA_TITLE = "extra_title"
    }

}
package com.example.cancerwise.ui.quizioner

import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import com.example.cancerwise.MainActivity
import com.example.cancerwise.data.ViewModelFactory
import com.example.cancerwise.databinding.ActivityResultBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


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

        startCountAnimation()

        binding.linearLayout2.visibility = View.GONE

        binding.btnOk.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


    private fun startCountAnimation() {
        val animator = ValueAnimator.ofInt(0, 100) //0 is min number, 600 is max number

        animator.duration = 5000 //Duration is in milliseconds
        animator.addUpdateListener { animation ->
            binding.count.text = animation.animatedValue.toString()

            val num = animation.animatedValue.toString()
            if (num.toInt() ==  40) {
                binding.count.setTextColor(Color.YELLOW)
            } else if (num.toInt() ==  70) {
                binding.count.setTextColor(Color.RED)
            }
        }
        animator.start()
        animator.doOnEnd {
            binding.process.text = "Completed"
            binding.linearLayout2.visibility = View.VISIBLE
        }
    }

}
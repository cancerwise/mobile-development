package com.example.cancerwise.ui.quizioner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.cancerwise.R
import com.example.cancerwise.ui.quizioner.QuizionerActivity.Companion.EXTRA_AGE
import com.example.cancerwise.ui.quizioner.QuizionerActivity.Companion.EXTRA_GENDER
import com.example.cancerwise.databinding.ActivityPreQuizBinding

class PreQuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPreQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPreQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.buttonStart.setOnClickListener {

            val age = binding.age.text.toString()
            var isMale = true


            if (binding.radioSex.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Please select your gender", Toast.LENGTH_SHORT).show()
            } else if (age.isEmpty()) {
                Toast.makeText(this, "Please add your age", Toast.LENGTH_SHORT).show()
            } else {

                binding.radioSex.setOnCheckedChangeListener { group, checkedId ->
                    if (R.id.radioMale == checkedId) {
                        isMale = true
                    }
                }

                val intent = Intent(this, QuizionerActivity::class.java)
                    .putExtra(EXTRA_AGE, age)
                    .putExtra(EXTRA_GENDER, isMale)

                startActivity(intent)
                finish()

            }

        }

        setUpData()

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

    }


    private fun setUpData() {

        val extraId: Int = intent.getIntExtra(EXTRA_ID, 1)
        val extraName = intent.getStringExtra(EXTRA_NAME)
        val extraDesc = intent.getStringExtra(EXTRA_DES)
        val extraImg: Int = intent.getIntExtra(EXTRA_IMAGE, 10)

        if (extraImg != null) {
            binding.img.setImageResource(extraImg)
        }

        binding.title.text = extraName
        binding.desc.text = extraDesc

    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DES = "extra_des"
        const val EXTRA_IMAGE = "extra_img"
    }
}





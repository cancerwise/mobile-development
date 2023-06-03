package com.example.cancerwise.ui.quizioner

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import com.example.cancerwise.MainActivity
import com.example.cancerwise.R
import com.example.cancerwise.model.FakeRewardDataSource
import com.example.cancerwise.model.QuestionAnswers
import com.example.cancerwise.model.QuizAnswer
import com.example.cancerwise.data.QuizRepository
import com.example.cancerwise.data.ViewModelFactory
import com.example.cancerwise.model.Quizioner
import com.example.cancerwise.databinding.ActivityQuizionerBinding
import com.example.cancerwise.ui.auth.AuthViewModel
import com.example.cancerwise.ui.auth.login.LoginBottomSheet
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class QuizionerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizionerBinding

    private val viewModel: QuizionerViewModel by viewModels {
        ViewModelFactory(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuizionerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        var currentProgress = 1
        var answer = ""

        var temp: MutableList<QuestionAnswers> = ArrayList()

        quizAdapter(FakeRewardDataSource.dummQuizioner, currentProgress)

        binding.boolAnswer.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId != -1) {
                binding.btnNext.isEnabled = true
                binding.btnEnd.isEnabled = true
                answer = if (checkedId == R.id.yes) {
                    "true"
                } else "false"
            } else {
                binding.btnNext.isEnabled = false
                binding.btnEnd.isEnabled = false
            }
        }

        binding.intAnswer.doAfterTextChanged {
            if (it != null ) {
                if (it.isNotEmpty() && it.toString() != "") {
                    binding.btnNext.isEnabled = true
                    binding.btnEnd.isEnabled = true
                    answer = it.toString()
                } else {
                    binding.btnNext.isEnabled = false
                    binding.btnEnd.isEnabled = false
                }
            }
        }

        binding.btnNext.setOnClickListener {

            temp.add(QuestionAnswers(currentProgress, answer))

            currentProgress++
            binding.boolAnswer.clearCheck()
            quizAdapter(FakeRewardDataSource.dummQuizioner, currentProgress)

            binding.intAnswer.text.clear()

        }

        binding.btnEnd.setOnClickListener {

            temp.add(QuestionAnswers(currentProgress, answer))

            binding.intAnswer.text.clear()

            val age = intent.getIntExtra(EXTRA_AGE, 1)
            val isMale = intent.getBooleanExtra(EXTRA_AGE, true)

            viewModel.uploadData(QuizAnswer(1, isMale, age, temp))

            var ans = ""

            val items = temp

            for (item in items) {
                ans += "${item.number} / ${item.answer} \n"
            }

            LoginBottomSheet.DialogController.showDialogConfirmation(
                this,
                "your answer?",
                ans
            ) {
                val intent = Intent(this, ResultActivity::class.java)

                startActivity(intent)
                finish()
            }
        }

        binding.exit.setOnClickListener {
            LoginBottomSheet.DialogController.showDialogConfirmation(
                this,
                "Are you sure?",
                "Your progress is going to be removed if continue"
            ) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                finish()
            }
        }
    }

    private fun quizAdapter(item: Quizioner, currentProgress: Int){

        val size = item.questions.size

        if (currentProgress == size) {
            binding.btnNext.visibility = View.INVISIBLE
            binding.btnEnd.visibility = View.VISIBLE
        }

        if (item.questions[currentProgress - 1].expectedAnswer == "boolean"){
            binding.boolAnswer.visibility = View.VISIBLE
            binding.intAnswer.visibility = View.GONE
        } else {
            binding.boolAnswer.visibility = View.GONE
            binding.intAnswer.visibility = View.VISIBLE
        }

        binding.title.text = item.name
        binding.indicator.text = "${currentProgress}/${size}"
        binding.progressBarHorizontal.max = size
        binding.progressBarHorizontal.progress = currentProgress

        binding.question.text = item.questions[currentProgress - 1].question
    }

    object DialogController {
        private lateinit var dialogAlert: AlertDialog
        private lateinit var dialogConfirmation: AlertDialog

        private fun cantShowDialog(): Boolean =
            this::dialogAlert.isInitialized && dialogAlert.isShowing ||
                    this::dialogConfirmation.isInitialized && dialogConfirmation.isShowing

        fun showDialogAlert(context: Context, title: String, msg: String) {
            if (cantShowDialog()) {
                return
            }

            dialogAlert = MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(msg)
                .setPositiveButton("Close", null)
                .setCancelable(false)
                .create()
                .apply {
                    setCanceledOnTouchOutside(false)
                    show()
                }
        }

        fun showDialogConfirmation(
            context: Context,
            title: String,
            msg: String,
            callback: () -> Unit
        ) {
            if (cantShowDialog()) {
                return
            }

            dialogConfirmation = MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(msg)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Confirm") { _, _ -> callback() }
                .setCancelable(false)
                .create()
                .apply {
                    setCanceledOnTouchOutside(false)
                    show()
                }
        }
    }


    companion object {
        const val EXTRA_AGE = "extra_age"
        const val EXTRA_GENDER = "extra_gender"
    }
}
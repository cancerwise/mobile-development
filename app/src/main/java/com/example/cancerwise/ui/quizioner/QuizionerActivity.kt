package com.example.cancerwise.ui.quizioner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import com.example.cancerwise.ui.MainActivity
import com.example.cancerwise.R
import com.example.cancerwise.data.Resource
import com.example.cancerwise.model.QuestionAnswers
import com.example.cancerwise.model.QuizAnswer
import com.example.cancerwise.data.ViewModelFactory
import com.example.cancerwise.model.Quizioner
import com.example.cancerwise.databinding.ActivityQuizionerBinding
import com.example.cancerwise.model.SimpleAnswers
import com.example.cancerwise.model.SimpleResponse
import com.example.cancerwise.ui.quizioner.ResultActivity.Companion.EXTRA_ACTION
import com.example.cancerwise.ui.quizioner.ResultActivity.Companion.EXTRA_RESULT
import com.example.cancerwise.ui.quizioner.ResultActivity.Companion.EXTRA_TITLE
import com.example.cancerwise.utils.Utils
import java.io.Serializable
import java.util.Objects

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

        binding.animationView.speed = 2.5F
        binding.animationView.visibility = View.GONE

        var currentProgress = 1
        var answer = ""

        val temp: MutableList<QuestionAnswers> = ArrayList()
        val simpleAnswers: ArrayList<String> = ArrayList()

        val id = intent.getIntExtra(EXTRA_ID, 0)
        val data = viewModel.getQuizData(id)
        quizAdapter(data, currentProgress)

        binding.boolAnswer.setOnCheckedChangeListener { _, checkedId ->
            if (checkedId != -1) {
                binding.btnNext.isEnabled = true
                binding.btnEnd.isEnabled = true
                answer = if (checkedId == R.id.yes) {
                    "1"
                } else "0"
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

            fun onSuccess() {
                temp.add(QuestionAnswers(currentProgress, answer))

                simpleAnswers.add(answer)

                currentProgress++
                binding.boolAnswer.clearCheck()
                quizAdapter(data, currentProgress)

                binding.intAnswer.text.clear()
            }

            when (data.questions[currentProgress - 1].note) {
                0 -> {
                    onSuccess()
                }
                1 -> {
                    onSuccess()
                }
                2 -> {
                    if (answer.toInt() > 7) {
                        Toast.makeText(this, "Please input correct answers", Toast.LENGTH_SHORT).show()
                        binding.intAnswer.error = "Please input correct answers"
                    } else {
                        onSuccess()
                    }
                }
                3 -> {
                    onSuccess()
                }
            }
        }

        binding.btnEnd.setOnClickListener {

            fun onSuccess() {
                temp.add(QuestionAnswers(currentProgress, answer))
                simpleAnswers.add(answer)

                binding.intAnswer.text.clear()
                viewModel.predictUpload(id, SimpleAnswers(simpleAnswers))

                viewModel.result.observe(this) {
                    when (it) {
                        is Resource.Error -> onFailed(it.message)
                        is Resource.Loading -> onLoading()
                        is Resource.Success -> it.data?.let { it1 ->
                            onSuccess(it1)
                        }
                    }
                }
            }

            when (data.questions[currentProgress - 1].note) {
                0 -> {
                    onSuccess()
                }
                1 -> {
                    onSuccess()
                }
                2 -> {
                    if (answer.toInt() > 7) {
                        Toast.makeText(this, "Please input correct answers", Toast.LENGTH_SHORT).show()
                        binding.intAnswer.error = "Please input correct answers"
                    } else {
                        onSuccess()
                    }
                }
                3 -> {
                    onSuccess()
                }
            }
        }

        binding.exit.setOnClickListener {
            Utils.DialogController.showDialogConfirmation(
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
        setUpNote(item.questions[currentProgress - 1].note)

        binding.question.text = item.questions[currentProgress - 1].question
    }

    private fun setUpNote(idNote: Int) {
        when(idNote) {
            0 -> binding.ansNote.visibility = View.GONE
            1 -> {
                binding.ansNote.visibility = View.GONE
            }
            2 -> {
                binding.ansNote.visibility = View.VISIBLE
                binding.ansNote.text = "Please put answers range from 1 - 7 for better prediction*"
            }
            3 -> {
                binding.ansNote.visibility = View.VISIBLE
                binding.ansNote.text =
                    "If you haven't taking the above test it will affect the accuracy of our predictions*"
            }
        }
    }
    private fun onSuccess(data: SimpleResponse) {

        binding.mainLy.visibility = View.VISIBLE
        binding.animationView.visibility = View.GONE

        val title = viewModel.getQuizData(intent.getIntExtra(EXTRA_ID, 0)).name
        val intent = Intent(this, ResultActivity::class.java)
            .putExtra(EXTRA_RESULT, data.status)
            .putExtra(EXTRA_ACTION, data.action)
            .putExtra(EXTRA_TITLE, title)

        startActivity(intent)
        finish()

    }

    private fun onLoading() {
        binding.mainLy.visibility = View.GONE
        binding.animationView.visibility = View.VISIBLE
    }

    private fun onFailed(message: String?) {
        Toast.makeText(this@QuizionerActivity, message.toString(), Toast.LENGTH_SHORT).show()

        binding.mainLy.visibility = View.GONE
        binding.animationView.visibility = View.VISIBLE

        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
        finish()
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}
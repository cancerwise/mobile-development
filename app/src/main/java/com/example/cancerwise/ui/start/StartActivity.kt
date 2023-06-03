package com.example.cancerwise.ui.start

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.cancerwise.ui.auth.AuthViewModel
import com.example.cancerwise.MainActivity
import com.example.cancerwise.data.ViewModelFactory
import com.example.cancerwise.databinding.ActivityStartBinding
import com.example.cancerwise.ui.auth.login.LoginBottomSheet
import com.example.cancerwise.ui.auth.register.RegisterBottomSheet


class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding
    private val viewModel: AuthViewModel by viewModels {
        ViewModelFactory(application)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        viewModel.getLoginState()

        viewModel.loginState.observe(this) {
            if (it) {
                Toast.makeText(this@StartActivity, "Logged in", Toast.LENGTH_LONG).show()
                val intent = Intent(this@StartActivity, MainActivity::class.java)
                startActivity(intent)
            } else Toast.makeText(this@StartActivity, "No Auth Data", Toast.LENGTH_LONG).show()
        }

        binding.buttonRegister.setOnClickListener {
            val bottomSheetFragmentRegister = RegisterBottomSheet()
            bottomSheetFragmentRegister.show(supportFragmentManager, "Register BottomSheet Dialog")
        }

        binding.buttonLogin.setOnClickListener {
            val bottomSheetFragmentLogin = LoginBottomSheet()
            bottomSheetFragmentLogin.show(supportFragmentManager, "Login ButtomSheet Dialog")
        }

        binding.btnConnectGoogle.setOnClickListener{
            Toast.makeText(this@StartActivity, "Not available yet", Toast.LENGTH_SHORT).show()
        }
    }
}
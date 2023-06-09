package com.example.cancerwise.ui.auth.login

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.cancerwise.ui.auth.AuthViewModel
import com.example.cancerwise.ui.MainActivity
import com.example.cancerwise.R
import com.example.cancerwise.data.AuthState
import com.example.cancerwise.data.ViewModelFactory
import com.example.cancerwise.databinding.BottomSheetLoginBinding
import com.example.cancerwise.utils.Utils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class LoginBottomSheet: BottomSheetDialogFragment() {

    private var _binding: BottomSheetLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels {
        ViewModelFactory(activity?.application ?: Application())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomSheetLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.authState.observe(this){

            when(it){
                AuthState.Idle -> {
                    binding.progressbar.visibility = View.GONE
                }
                AuthState.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                AuthState.Success -> {
                    binding.progressbar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_LONG).show()
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(intent)
                    dialog?.dismiss()
                    activity?.finish()
                }
                else -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    binding.progressbar.visibility = View.GONE
                }
            }
        }

        binding.login.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()

            if (email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email ?: "").matches()) {
                binding.email.error = resources.getString(R.string.invalidEmail)
            } else if (password.isEmpty() && password.length < 6) {
                binding.password.error = resources.getString(R.string.invalidPassword)
            } else {
               viewModel.handleSignIn(email, password)
            }
        }

        binding.resetPass.setOnClickListener {
            Utils.DialogController.showDialogConfirmation(
                requireContext(),
                "Reset Password?",
                "We will send reset password request into your email"
            ) {
                val email = binding.email.text.toString()

                if (email.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(email ?: "").matches()) {
                    binding.email.error = resources.getString(R.string.invalidEmail)
                } else {
                    viewModel.resetPassword(email)
                    viewModel.authState.observe(this) {
                        when (it) {
                            AuthState.Error(it.message) -> Toast.makeText(
                                requireContext(),
                                it.message,
                                Toast.LENGTH_LONG
                            ).show()

                            else -> Toast.makeText(
                                requireContext(),
                                "Reset password request have been sent into your email",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

            }
        }
    }
}

package com.example.cancerwise.data

sealed class AuthState(val message: String? = null) {
    object Idle : AuthState()
    object Loading : AuthState()
    object Success : AuthState()
    class Error(message: String?) : AuthState(message)
}
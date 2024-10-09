package com.embarkapps.inscribe.presentation.signin

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(

) : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun updateEmail(newEmail: String) {
        _state.update {
            SignInState(
                email = newEmail,
                password = _state.value.password
            )
        }
    }

    fun updatePassword(newPassword: String) {
        _state.update {
            SignInState(
                email = _state.value.email,
                password = newPassword
            )
        }
    }

    fun onSignInClick() {
        Log.d("SignInViewModel", "email: ${_state.value.email}, password: ${state.value.password}")
    }
}
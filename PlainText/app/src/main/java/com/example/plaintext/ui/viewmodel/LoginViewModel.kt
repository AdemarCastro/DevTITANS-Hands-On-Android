package com.example.plaintext.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class LoginViewState(
    val login: String = "",
    val senha: String = "",
    val salvarInformacao: Boolean = false
)

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    var loginViewState by mutableStateOf(LoginViewState())
        private set

    fun updateLogin(login: String) {
        loginViewState = loginViewState.copy(
            login = login
        )
    }

    fun updateSenha(senha: String) {
        loginViewState = loginViewState.copy(
            senha = senha
        )
    }

    fun updateSalvarInformacao(salvarInformacao: Boolean) {
        loginViewState = loginViewState.copy(
            salvarInformacao = salvarInformacao
        )
    }
}
package com.example.plaintext.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class PreferencesState(
    var login: String,
    var password: String,
    var preencher: Boolean
)

@HiltViewModel
class PreferencesViewModel @Inject constructor(
    handle: SavedStateHandle,
) : ViewModel() {
    //val senhaState: Any
    var preferencesState by mutableStateOf(PreferencesState(login = "devtitans", password = "123", preencher = true))
        private set

    fun updateLogin(login: String) {

    }

    fun updatePassword(password: String) {

    }

    fun updatePreencher(preencher: Boolean) {

    }

    fun checkCredentials(login: String, password: String): Boolean{
        return login == preferencesState.login && password == preferencesState.password
    }
}
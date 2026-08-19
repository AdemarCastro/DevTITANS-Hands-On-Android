package com.example.plaintext.ui.screens.preferences


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.plaintext.ui.screens.login.TopBarComponent
import com.example.plaintext.ui.screens.util.PreferenceInput
import com.example.plaintext.ui.screens.util.PreferenceItem
import com.example.plaintext.ui.viewmodel.PreferencesViewModel


@Composable
fun SettingsScreen(
    navController: NavHostController?,
    viewModel: PreferencesViewModel? = if (LocalInspectionMode.current) null else hiltViewModel()
){
    Scaffold(
        topBar = {
            TopBarComponent(title = "Plain Text")
        }
    ){ padding ->
        SettingsContent(modifier = Modifier.padding(padding), viewModel = viewModel)
    }
}

@Composable
fun SettingsContent(modifier: Modifier = Modifier,
                    viewModel: PreferencesViewModel? = null) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())){

        PreferenceInput(
            title = "Preencher Login",
            label = "Login",
            fieldValue = "",
            summary = "Preencher login na tela inicial"
        ){
            // função para alterar o login
        }

        PreferenceInput(
            title = "Setar Senha",
            label = "Label",
            fieldValue = "",
            summary = "Senha para entrar no sistema"
        ){
            // função para alterar a senha
        }

        PreferenceItem(
            title = "Preencher Login",
            summary = "Preencher login na tela inicial",
            onClick = {
                // deve alterar o estado que representa se o switch está ligado ou não
            },
            control = {
                Switch(
                    checked = false, // deve ler o estado que representa se o switch está ligado ou não
                    onCheckedChange = {
                        // deve alterar o estado que representa se o switch está ligado ou não
                    }
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    Scaffold(
        topBar = { TopBarComponent(title = "Plain Text") }
    ) { padding ->
        SettingsContent(modifier = Modifier.padding(padding))
    }
}
package com.example.plaintext.ui.screens.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.plaintext.R
import com.example.plaintext.ui.theme.PlainTextTheme
//import com.example.plaintext.ui.viewmodel.PreferencesViewModel
import com.example.plaintext.ui.viewmodel.LoginViewModel

data class LoginState(
    val preencher: Boolean,
    val login: String,
    val navigateToSettings: () -> Unit,
    val navigateToList: (name: String) -> Unit,
    val checkCredentials: (login: String, password: String) -> Boolean,
)

@Composable
fun Login_screen(
    navigateToSettings: () -> Unit,
    navigateToList: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel? = if (LocalInspectionMode.current) null else hiltViewModel()
) {
//    var login by remember { mutableStateOf("") }
//    var senha by remember { mutableStateOf("") }
//    var salvarInformacao by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val login = viewModel?.loginViewState?.login ?: ""
    val senha = viewModel?.loginViewState?.senha ?: ""
    val salvarInformacao = viewModel?.loginViewState?.salvarInformacao ?: false
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Row(modifier = Modifier.fillMaxWidth()
            .background(Color(0xFF8DB600)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp),

        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(72.dp)
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "\"The most secure password manager\"",
                    style = MaterialTheme.typography.titleMedium,
                    color = androidx.compose.ui.graphics.Color.White
                )
                Text(
                    text = "Bob and Alice",
                    style = MaterialTheme.typography.titleMedium,
                    color = androidx.compose.ui.graphics.Color.White
                )
            }
        }
        Text(text = "Digite suas credenciais para continuar")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

      //------------------------------------------------------ login e senha
            Text(text = "Login:", modifier = Modifier.weight(1f))
            OutlinedTextField(
                value = login,
                onValueChange = { viewModel?.updateLogin(it) },
                modifier = Modifier.weight(3f),
                singleLine = true
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Senha:", modifier = Modifier.weight(1f))
            OutlinedTextField(
                value = senha,
                onValueChange = { viewModel?.updateSenha(it) },
     //----------------------------------------------------
                modifier = Modifier.weight(3f),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
        //---------Checkbox atualizado-----------------
            Checkbox(
                checked = salvarInformacao,
                onCheckedChange = {
                    viewModel?.updateSalvarInformacao(it)
                }
            )
            Text(text = "Salvar informações de Login")
        }
        Button(
            onClick = {
                Toast.makeText(context, "Olá", Toast.LENGTH_SHORT).show()
            },
            enabled = true
        ) { Text(text = "Enviar")}
    }

}

@Composable
fun MyAlertDialog(shouldShowDialog: MutableState<Boolean>) {
    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
                shouldShowDialog.value = false
            },

            title = { Text(text = "Sobre") },
            text = { Text(text = "PlainText Password Manager v1.0") },
            confirmButton = {
                Button(
                    onClick = { shouldShowDialog.value = false }
                ) {
                    Text(text = "Ok")
                }
            }
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TopBarComponent(
    navigateToSettings: (() -> Unit?)? = null,
    navigateToSensores: (() -> Unit?)? = null,
    title: String,
) {
    var expanded by remember { mutableStateOf(false) }
    val shouldShowDialog = remember { mutableStateOf(false) }

    if (shouldShowDialog.value) {
        MyAlertDialog(shouldShowDialog = shouldShowDialog)
    }

    TopAppBar(
        title = { Text("PlainText") },
        actions = {
            if (navigateToSettings != null && navigateToSensores != null) {
                IconButton(onClick = { expanded = true }) {
                    Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Configurações") },
                        onClick = {
                            navigateToSettings();
                            expanded = false;
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                    DropdownMenuItem(
                        text = {
                            Text("Sobre");
                        },
                        onClick = {
                            shouldShowDialog.value = true;
                            expanded = false;
                        },
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    )
}

//  Preview screen
@Preview(showBackground = true)
@Composable
fun TopBarComponentPreview() {
    PlainTextTheme {
        TopBarComponent(title = "Plain Text")
    }
}

@Preview(showBackground = true)
@Composable
fun logginPreview() {
    PlainTextTheme {
        Login_screen(
            navigateToSettings = {},
            navigateToList = {},
            viewModel = null
        )
    }
}


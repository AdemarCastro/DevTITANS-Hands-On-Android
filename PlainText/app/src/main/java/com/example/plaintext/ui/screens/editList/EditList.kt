package com.example.plaintext.ui.screens.editList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.widget.Toast
import com.example.plaintext.data.model.PasswordInfo
import com.example.plaintext.ui.screens.Screen
import com.example.plaintext.ui.screens.login.TopBarComponent

data class EditListState(
    val nomeState: MutableState<String>,
    val usuarioState: MutableState<String>,
    val senhaState: MutableState<String>,
    val notasState: MutableState<String>,
)

fun isPasswordEmpty(password: PasswordInfo): Boolean {
    return password.name.isEmpty() && password.login.isEmpty() &&
        password.password.isEmpty() && password.notes.isNullOrEmpty()
}

@Composable
fun EditList(
    args: Screen.EditList,
    navigateBack: () -> Unit,
    savePassword: (password: PasswordInfo) -> Unit
) {
    val context = LocalContext.current
    var name by rememberSaveable(args.password.id) { mutableStateOf(args.password.name) }
    var login by rememberSaveable(args.password.id) { mutableStateOf(args.password.login) }
    var password by rememberSaveable(args.password.id) { mutableStateOf(args.password.password) }
    var notes by rememberSaveable(args.password.id) { mutableStateOf(args.password.notes.orEmpty()) }

    Scaffold(topBar = { TopBarComponent(title = args.title) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = login,
                onValueChange = { login = it },
                label = { Text("Login") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Senha") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
            OutlinedTextField(
                value = notes,
                onValueChange = { notes = it },
                label = { Text("Notas") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            Button(
                onClick = {
                    if (name.isBlank() || login.isBlank() || password.isBlank()) {
                        Toast.makeText(
                            context,
                            "Preencha nome, login e senha",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        savePassword(
                            PasswordInfo(
                                id = args.password.id,
                                name = name,
                                login = login,
                                password = password,
                                notes = notes
                            )
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Salvar")
            }
            Button(
                onClick = navigateBack,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancelar")
            }
        }
    }
}


@Composable
fun EditInput(
    textInputLabel: String,
    textInputState: MutableState<String> = mutableStateOf(""),
    textInputHeight: Int = 60
) {
    var textState by rememberSaveable { textInputState }
    OutlinedTextField(
        value = textState,
        onValueChange = { textState = it },
        label = { Text(textInputLabel) },
        modifier = Modifier
            .fillMaxWidth()
            .height(textInputHeight.dp)
    )
    Spacer(modifier = Modifier.height(10.dp))
}

@Preview(showBackground = true)
@Composable
fun EditListPreview() {
    EditList(
        args = Screen.EditList(
            password = PasswordInfo(
                id = 1,
                name = "Nome",
                login = "Usuário",
                password = "Senha",
                notes = "Notas"
            ),
            title = "Editar Senha"
        ),
        navigateBack = {},
        savePassword = {}
    )
}

@Preview(showBackground = true)
@Composable
fun EditInputPreview() {
    EditInput("Nome")
}

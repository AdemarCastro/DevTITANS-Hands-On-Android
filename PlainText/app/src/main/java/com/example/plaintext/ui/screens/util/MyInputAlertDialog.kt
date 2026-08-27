package com.example.plaintext.ui.screens.util

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue


@Composable
fun MyInputAlertDialog(
    shouldShowDialog: MutableState<Boolean>,
    title: String,
    label: String,
    fieldValue: String? = null,
    onFinish: ((field: String) -> Unit)? = null
) {
    val defaultValue = fieldValue ?: ""
    val field = remember(fieldValue) {
        mutableStateOf(TextFieldValue(defaultValue, TextRange(defaultValue.length)))
    }
    val focusRequester = remember { FocusRequester() }

    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = {
                shouldShowDialog.value = false
            },
            title = { Text(text = title) },
            text = {
                OutlinedTextField(
                    value = field.value,
                    label = { Text(label) },
                    onValueChange = {
                        field.value = it
                    },
                    modifier = Modifier.focusRequester(focusRequester)
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        shouldShowDialog.value = false
                        onFinish?.invoke(field.value.text)
                    }
                ) {
                    Text(text = "Ok")
                }
            }
        )

        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}

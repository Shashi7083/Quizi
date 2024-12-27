package com.srdev.myapplication.quiz.presentation.common

import androidx.activity.compose.BackHandler
import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun BackHandlerWithConfirmation(
    onExit: () -> Unit,
    onStay : (Boolean) ->Unit,
    title: String = "",
    text: String = "",
    dismissText: String = "",
    confirmText: String = ""
) {
    var showDialog by remember { mutableStateOf(true) }

    BackHandler {
        showDialog = true // Show confirmation dialog on back press
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = onExit) {
                    Text(confirmText)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                    onStay(false)
                }) {
                    Text(dismissText)

                }
            },
            title = { Text(title) },
            text = { Text(text) }
        )
    }


}

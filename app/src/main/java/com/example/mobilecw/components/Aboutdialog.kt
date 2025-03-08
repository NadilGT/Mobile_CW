package com.example.mobilecw.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun AboutDialog(onDismiss: () -> Unit, text1:String, text2:String) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text1) },
        text = { Text(text2) },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}
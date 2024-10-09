package com.embarkapps.inscribe.presentation.signin.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun LoginOutlinedTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = label) },
        value = value,
        onValueChange = { onValueChange(it) })
}
package com.themplate.cmp

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
actual fun CustomButton(label: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
    ) {
        Text(text = label)
    }
}
package com.vanyscore.app.composes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun BackButton(onClick: () -> Unit) {
    return IconButton(onClick) {
        Icon(Icons.Default.ArrowBack, "back",
            tint = MaterialTheme.colorScheme.onPrimary,
        )
    }
}
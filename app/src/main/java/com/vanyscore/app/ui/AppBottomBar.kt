package com.vanyscore.app.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

private class AppRoute(
    val title: String,
    val icon: ImageVector
)

@Composable
fun AppBottomBar() {
    val routes = listOf(
        AppRoute(title = "Заметки", icon = Icons.Default.Edit),
        AppRoute(title = "Задачи", icon = Icons.Default.Build),
    )
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primary,
        tonalElevation = 2.dp,
        modifier = Modifier.height(116.dp)
    ) {
        routes.forEach { route ->
            NavigationBarItem(false, onClick = {}, icon = {
                Icon(
                    route.icon,
                    modifier = Modifier
                        .size(16.dp),
                    contentDescription = "notes",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }, label = {
                Text(route.title, style = TextStyle(
                    color = MaterialTheme.colorScheme.onPrimary
                ))
            },)
        }
    }
}
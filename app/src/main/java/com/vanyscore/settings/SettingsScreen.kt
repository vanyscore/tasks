package com.vanyscore.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vanyscore.app.composes.BackButton
import com.vanyscore.app.navigation.LocalNavController
import com.vanyscore.app.ui.noIndicationClickable
import com.vanyscore.settings.dialogs.ThemeDialog
import com.vanyscore.app.viewmodel.AppViewModel
import com.vanyscore.app.viewmodel.LocalAppViewModel
import com.vanyscore.tasks.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val navController = LocalNavController.current
    val isThemeDialogShow = remember { mutableStateOf(false) }
    val isThemeDialogShowOn = isThemeDialogShow.value
    val viewModel = LocalAppViewModel.current
    return Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.settings),
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onPrimary,
                            fontSize = 16.sp,
                        )
                    )
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    BackButton {
                        navController.navigateUp()
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            SettingsItem("Выбор темы") {
                isThemeDialogShow.value = true
            }
        }
        if (isThemeDialogShowOn) {
            ThemeDialog(
                onSelect = { theme ->
                    viewModel.setTheme(theme)
                }
            ) {
                isThemeDialogShow.value = false
            }
        }
    }
}

@Composable
private fun SettingsItem(
    title: String,
    onClick: () -> Unit
) {
    return Column {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .noIndicationClickable(onClick = onClick)
                .fillMaxWidth()
        ) {
            Text(text = title, style = TextStyle(
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.scrim
            ))
        }
        Divider(
            thickness = 1.dp,
        )
    }
}
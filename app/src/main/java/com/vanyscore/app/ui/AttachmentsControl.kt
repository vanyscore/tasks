package com.vanyscore.app.ui

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.vanyscore.tasks.R

enum class ControlType {
    ROW, GRID
}

@Composable
fun AttachmentsControl(
    controlType: ControlType = ControlType.ROW,
    attachments: List<Uri> = emptyList(),
    onAttachmentAdd: ((uri: Uri) -> Unit)? = null,
    onAttachmentRemove: ((uri: Uri) -> Unit)? = null,
) {
    val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) {
        val uri = it
        Log.d("image", "Selected uri: $uri")
        if (uri != null) {
            onAttachmentAdd?.invoke(uri)
        }
    }
    return Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        val cellSize = (LocalConfiguration.current.screenWidthDp / 3)
        val rows = attachments.size / 3
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.attachments)
            )
            IconButton(
                onClick = {
                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }
            ) {
                Icon(
                    Icons.Default.Add, "add_attachment"
                )
            }
        }
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.height(
                (rows * cellSize + 20).dp
            )
        ) {
            items(
                count = attachments.size
            ) { index ->
                val attachment = attachments[index]
                AsyncImage(
                    attachment, "async_image",
                    modifier = Modifier.size(cellSize.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
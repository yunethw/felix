package com.felix.felix.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun IconBox(imageVector: ImageVector, size: Dp) {
    Image(
        imageVector = imageVector,
        contentDescription = "Ratiings",
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
        alignment = Alignment.Center,
        contentScale = ContentScale.Inside,
        modifier = Modifier
            .border(
                border = BorderStroke(2.dp, MaterialTheme.colorScheme.surfaceVariant),
                shape = RoundedCornerShape(8.dp)
            )
            .size(size)
    )
}
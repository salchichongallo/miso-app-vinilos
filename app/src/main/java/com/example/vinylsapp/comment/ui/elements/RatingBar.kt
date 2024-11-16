package com.example.vinylsapp.comment.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(stars: Int? = null, onPressed: ((Int) -> Unit)? = null, size: Dp = 20.dp) {
    val currentStars = stars ?: -1;
    Row {
        for (star in 0..4) {
            val color =
                if (currentStars > star) colorScheme.primary else colorScheme.inversePrimary

            var modifier = Modifier.size(size).testTag("Star$star")
            if (onPressed != null) {
                modifier = modifier.clickable {
                    val newStars = if (star + 1 == currentStars) 0 else star + 1
                    onPressed(newStars)
                }
            }

            Icon(
                Icons.Filled.Star,
                contentDescription = null,
                tint = color,
                modifier = modifier,
            )
        }
    }
}

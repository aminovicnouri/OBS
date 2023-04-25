package com.aminovic.obs.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.aminovic.obs.domain.modal.AthleteResult
import com.aminovic.obs.presentation.ui.theme.Colors

@Composable
fun MedalsRow(
    modifier: Modifier = Modifier,
    results: AthleteResult
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text(text = "${results.city}", Modifier.width(150.dp))
        if (results.gold!! > 0) {
            Spacer(modifier = Modifier.width(5.dp))
            Medal(number = results.gold, color = Colors.Gold)
        }
        if (results.silver!! > 0) {
            Spacer(modifier = Modifier.width(5.dp))
            Medal(number = results.silver, color = Colors.Silver)
        }
        if (results.bronze!! > 0) {
            Spacer(modifier = Modifier.width(5.dp))
            Medal(number = results.bronze, color = Colors.Bronze)
        }
    }
}

@Composable
fun Medal(
    number: Int,
    size: Dp = 28.dp,
    color: Color
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(size)
            .aspectRatio(1f)
            .background(color, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "$number", color = Yellow, textAlign = TextAlign.Center)
    }
}

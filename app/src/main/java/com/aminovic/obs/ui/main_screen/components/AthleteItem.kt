package com.aminovic.obs.ui.main_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.aminovic.obs.data.remote.ObsApi.Companion.BASE_URL
import com.aminovic.obs.domain.modal.Athlete
import com.aminovic.obs.ui.theme.Colors

@Composable
fun AthleteItem(athlete: Athlete, onClick: (String, String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = "${BASE_URL}athletes/${athlete.athleteId}/photo",
            contentDescription = "athlete ${athlete.athleteId} photo",
            modifier = Modifier
                .size(70.dp)
                .clip(shape = CircleShape)
                .background(color = Colors.DarkBlue)
                .clickable { onClick(athlete.athleteId, "${athlete.name} ${athlete.surname}") },
            contentScale = ContentScale.Fit
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "${athlete.name} ${athlete.surname}",
            style = TextStyle(
                fontSize = 12.sp
            ),
            color = MaterialTheme.colors.onSurface
        )
    }
}
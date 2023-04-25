package com.aminovic.obs.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.aminovic.obs.R
import com.aminovic.obs.data.remote.ObsApi
import com.aminovic.obs.domain.modal.Athlete
import com.aminovic.obs.presentation.ui.theme.Colors

@Composable
fun AthleteCard(
    athleteId: String,
    athleteName: String,
    athlete: Athlete?,
    backgroundColor: Color
) {
    Card(
        backgroundColor = backgroundColor,
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = "${ObsApi.BASE_URL}athletes/${athleteId}/photo",
                contentDescription = "athlete $athleteId photo",
                modifier = Modifier
                    .size(200.dp)
                    .clip(shape = CircleShape)
                    .background(color = Colors.DarkBlue),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = athleteName,
                fontSize = 30.sp,
                color = Color.White,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.dob),
                    fontSize = 15.sp,
                    color = Color.White
                )
                athlete?.let {
                    Text(
                        text = athlete.dateOfBirth ?: "N/A",
                        fontSize = 15.sp,
                        color = Color.White
                    )
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.weight),
                        color = MaterialTheme.colors.onSurface,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    athlete?.let {
                        Text(
                            text = "${athlete.weight} kg",
                            color = MaterialTheme.colors.onSurface,
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = stringResource(R.string.height),
                        color = MaterialTheme.colors.onSurface,
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    athlete?.let {
                        Text(
                            text = "${athlete.height} cm",
                            color = MaterialTheme.colors.onSurface,
                        )
                    }
                }
            }
        }
    }
}
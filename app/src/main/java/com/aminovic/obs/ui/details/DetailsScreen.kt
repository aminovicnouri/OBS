package com.aminovic.obs.ui.details

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aminovic.obs.R
import com.aminovic.obs.ui.details.components.AthleteCard
import com.aminovic.obs.ui.details.components.ExpandedText
import com.aminovic.obs.ui.details.components.MedalsRow
import com.aminovic.obs.ui.theme.Colors.DarkBlue
import com.aminovic.obs.ui.theme.Colors.Gold

@Composable
fun DetailsScreen(
    state: DetailsState,
    popup: () -> Unit,
    athleteId: String,
    athleteName: String,
    onEvent: (DetailsEvent) -> Unit
) {
    val isRotated = rememberSaveable { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isRotated.value) 360F else 0F,
        animationSpec = tween(durationMillis = 500, easing = FastOutLinearInEasing)
    )

    LaunchedEffect(key1 = true) {
        onEvent(DetailsEvent.GetAthlete(athleteId = athleteId))
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        TopAppBar(
            elevation = 5.dp,
            backgroundColor = DarkBlue,
            content = {
                IconButton(onClick = popup) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back_button),
                        tint = MaterialTheme.colors.onBackground,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Text(
                    text = athleteName,
                    fontSize = 20.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onBackground,
                        fontSize = 20.sp
                    )
                )
            },
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(state = rememberScrollState())
                .weight(1f, fill = false)
                .background(MaterialTheme.colors.background)
                .padding(all = 32.dp),
        ) {
            AthleteCard(
                athleteName = athleteName,
                athlete = state.athlete,
                athleteId = athleteId,
                backgroundColor = DarkBlue
            )

            if (state.isLoading) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
            state.error?.let {
                Column(
                    Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = it,
                        color = MaterialTheme.colors.onSurface,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    IconButton(onClick = {
                        isRotated.value = !isRotated.value
                        onEvent(DetailsEvent.GetAthlete(athleteId))
                    }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = stringResource(R.string.refresh_button),
                            tint = MaterialTheme.colors.onBackground,
                            modifier = Modifier
                                .padding(16.dp)
                                .rotate(rotationAngle)
                        )
                    }
                }
            }
            state.athlete?.let { athlete ->
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(R.string.medals), fontSize = 24.sp)
                Spacer(modifier = Modifier.height(5.dp))
                for (medal in state.athlete.results) {
                    MedalsRow(results = medal)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = stringResource(R.string.bio), fontSize = 24.sp)
                ExpandedText(
                    text = athlete.bio!!.substring(0, 100),
                    expandedText = athlete.bio,
                    expandedTextButton = " more",
                    shrinkTextButton = " less",
                    textStyle = MaterialTheme.typography.body1.copy(color = Color.White.copy(alpha = 0.6f)),
                    expandedTextStyle = MaterialTheme.typography.body1.copy(
                        color = Color.White.copy(
                            alpha = 0.8f
                        )
                    ),
                    expandedTextButtonStyle = MaterialTheme.typography.body1.copy(
                        color = Gold,
                    ),
                    shrinkTextButtonStyle = MaterialTheme.typography.body1.copy(
                        color = Gold,
                    ),
                    modifier = Modifier
                        .padding(top = 5.dp, start = 8.dp, end = 8.dp)
                )
            }
        }
    }
}
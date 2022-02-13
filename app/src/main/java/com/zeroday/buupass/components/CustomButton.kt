package com.zeroday.buupass.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    color: Color,
    strokeWidth: Dp,
    percentage: Float,
    animDuration: Int = 2500,
    animDelay: Int = 1150,
) {

    var animationPlayed by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }


    val sweepAngle = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(durationMillis = animDuration, delayMillis = animDelay)
    )


    Box(modifier = modifier, contentAlignment = Alignment.Center) {

        Canvas(modifier = Modifier.size(50.dp)) {
            drawArc(
                color = color,
                startAngle = 20f,
                sweepAngle = 360 * sweepAngle.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        ConstraintLayout(
            modifier = Modifier.size(40.dp)
                .background(
                    color = Color.White,
                    shape = MaterialTheme.shapes.small.copy(CornerSize(14.dp))
                )
                .padding(10.dp)
        ) {
            val (arrow) = createRefs()
            Icon(
                Icons.Filled.ArrowForward,
                contentDescription = "",
                modifier = Modifier.constrainAs(arrow) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    top.linkTo(parent.top)
                })

        }
    }
}
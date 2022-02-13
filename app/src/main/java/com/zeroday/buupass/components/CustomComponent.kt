package com.zeroday.buupass.components



import android.graphics.Bitmap
import androidx.annotation.ColorRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomComponent(
    modifier :Modifier = Modifier,
    percentage: Float,
    radius: Dp = 50.dp,
    color: Color,
    strokeWidth: Dp,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    img : Bitmap
) {


    var animationPlayed by remember {
        mutableStateOf(false)
    }


    val animatedIndicatorValue = remember {
        mutableStateOf(0f)
    }

    LaunchedEffect(key1 = true){
        animationPlayed = true
    }


    val sweepAngle = animateFloatAsState(
        targetValue = if(animationPlayed)percentage else 0f,
        animationSpec = tween(durationMillis = animDuration, delayMillis =  animDelay)
    )

    Box(modifier = modifier, contentAlignment = Alignment.Center, ) {

        Canvas(modifier = Modifier.size(58.dp)){
            drawArc(
                color = color,
                startAngle = 20f,
                sweepAngle = 360 * sweepAngle.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap= StrokeCap.Round)
            )
        }

        Image(
            bitmap = img.asImageBitmap(),
            contentDescription = "",
            modifier = Modifier
                .requiredWidth(55.dp)
                .requiredHeight(55.dp)
                .padding(2.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

    }
}


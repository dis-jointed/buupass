package com.zeroday.buupass.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BoxScope.Indicators(size : Int, index: Int, color: Color){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.align(alignment = Alignment.CenterStart)
    ) {

        repeat(size){

            //this a single indicator
            val isSelected = it == index

            val width = animateDpAsState(
                targetValue = if (isSelected) 25.dp else 10.dp,
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)
            )

            Box(
                modifier = Modifier
                    .height(10.dp)
                    .width(width.value)
                    .clip(CircleShape)
                    .background(
                        if (isSelected) color
                        else Color.Black.copy(alpha = 0.5f)
                    )
            )
        }
    }
}
package com.zeroday.buupass.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.zeroday.buupass.model.Car

@Composable
fun GridCard(
    item: Car,
    small: Boolean
) {

    CarCard(car = item, section = "available", height = item.height)
}
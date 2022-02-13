package com.zeroday.buupass.model

import androidx.annotation.DrawableRes
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Car(
    @DrawableRes var image: Int? = null,
    var name: String? = null,
    var price: String? = null,
    var method: String? = null,
    var year: String? = null,
    var height: Dp = 210.dp
)
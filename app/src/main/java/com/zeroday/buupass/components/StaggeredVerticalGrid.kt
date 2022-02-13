package com.zeroday.buupass.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.unit.Dp
import com.zeroday.buupass.model.Car
import kotlin.math.ceil


@Composable
fun GridRow2Elements(row: RowData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        GridCard(row.datas[0], small = true)
        GridCard(row.datas[1], small = true)
        GridCard(row.datas[2], small = false)
        GridCard(row.datas[3], small = true)
        GridCard(row.datas[4], small = true)
        GridCard(row.datas[5], small = true)
    }
}

@Composable
fun GridRow4Elements(row: RowData) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column {
            GridCard(row.datas[0], small = true, )
            GridCard(row.datas[1], small = false,)
            GridCard(row.datas[2], small = true,)

        }
        Column {
            GridCard(row.datas[3], small = false)
            GridCard(row.datas[4], small = true)
        }
    }
}


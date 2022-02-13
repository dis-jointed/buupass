package com.zeroday.buupass.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zeroday.buupass.model.Car

@Composable
fun Grid(rows: List<RowData>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        rows.mapIndexed { index, rowData ->
            if (rowData.datas.size == 2) {
                GridRow2Elements(rowData)
            } else if (rowData.datas.size == 4) {
                GridRow4Elements(rowData)
            }
        }
    }
}
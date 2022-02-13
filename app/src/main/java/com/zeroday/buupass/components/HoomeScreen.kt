package com.zeroday.buupass.components

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.constraintlayout.compose.ConstraintLayout
import com.zeroday.buupass.components.homecomponents.BusScreen
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HoomeScreen() {

    val context = LocalContext.current as AppCompatActivity

    val year: Int
    val month: Int
    val day: Int

    val currentMillis = remember {
        mutableStateOf(System.currentTimeMillis())
    }

    val futureMillis = remember {
        mutableStateOf(System.currentTimeMillis())
    }
    val sdf = SimpleDateFormat("EEE, MMM dd", Locale.getDefault())

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_WEEK)
    calendar.time = Date()
    
    val todaysDate = sdf.format(calendar.time)

    val section = remember {
        mutableStateOf("depart")
    }

    val date = remember {
        mutableStateOf("Pick Date")
    }

    val returnDate = remember {
        mutableStateOf(todaysDate)
    }

    //date picker dialog
    //higher order function
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayofMonth: Int ->


           // val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            val sdf = SimpleDateFormat("EEE, MMM dd", Locale.getDefault())

            val year = year
            val month = month
            val day = dayofMonth

            //fix for getting accurate date and not 39..
            val datee = Date(year-1900, month, day)

            val pickedDate = sdf.format(datee)

            calendar.set(year, month, day)

            if (section.value == "depart"){
                currentMillis.value = calendar.timeInMillis
            }else{
                futureMillis.value = calendar.timeInMillis
            }

            Log.d("TAG", "HoomeScreen: ${calendar.timeInMillis}")

            val formatedDate = pickedDate

            if (section.value == "depart"){
                date.value = formatedDate
            }else {
                returnDate.value = formatedDate
            }

        }, year, month, day
    )

    val verticalBottom = Brush.verticalGradient(
        0.2f to Color.Blue.copy(alpha = 0.55f),
        0.4f to Color.Red.copy(alpha = 0.65f),
        0.8f to Color.Blue.copy(0.85f),
        0.9f to Color.Blue,
        startY = 0.0f,
        endY = Float.POSITIVE_INFINITY
    )


    Surface() {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = verticalBottom)
        ) {
            BusScreen(
                openDatePicker = {
                    section.value = it
                    //disabling past dates
                    //restoring min date to current system time
                    if (it.equals("depart")){
                        datePickerDialog.datePicker.setMinDate(System.currentTimeMillis())
                    }else{
                        //making user not to choose past date from what he's chosen
                        datePickerDialog.getDatePicker().setMinDate(calendar.timeInMillis)
                    }


                    Log.d("TAG", "HoomeScreen: ${calendar.timeInMillis}")
                    datePickerDialog.show()
                },
                date = date.value,
                returnDate = returnDate.value,
                checkDates = {

                    //checking if return date is before depart date
                    if (currentMillis.value <= futureMillis.value){
                        Log.d("TAG", "HoomeScreen: Nice One")
                    }else{
                        Log.d("TAG", "HoomeScreen: Fix return date")
                    }
                }
            )
        }
    }
}
package com.zeroday.buupass.components.homecomponents

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.android.material.datepicker.MaterialDatePicker
import com.zeroday.buupass.R
import com.zeroday.buupass.components.NavButton

@Composable
fun BusScreen(
    openDatePicker: (String) -> Unit,
    date: String,
    returnDate: String,
    checkDates: () -> Unit
) {

    val activity = LocalContext.current as AppCompatActivity

    val verticalBottom = Brush.verticalGradient(
        0.1f to colorResource(id = R.color.backcolorone),
        0.42f to colorResource(id = R.color.backcolortwo),
        0.5f to Color.White,
        startY = 0.0f,
        endY = Float.POSITIVE_INFINITY
    )

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .background(verticalBottom)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxWidth()
                .padding(
                    top = 70.dp,
                    end = 5.dp
                )
        ) {

            val (leftdeptCont, rightDeptcont, navContainer, deptArriveCont, butontst) = createRefs()

            ConstraintLayout(
                modifier = Modifier.constrainAs(leftdeptCont) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
            ) {
                val (lineOne, directionIcon, lineTwo) = createRefs()

                val color = Color.Black

                Canvas(modifier = Modifier
                    .wrapContentSize()
                    .requiredWidth(10.dp)
                    .requiredHeight(100.dp)
                    .constrainAs(lineOne) {
                        top.linkTo(parent.top, 25.dp)
                        start.linkTo(parent.start, 27.dp)
                    }, onDraw = {

                    //line one
                    drawLine(
                        color = color,
                        start = Offset(2f, -20f),
                        end = Offset(2f, 190f),
                        strokeWidth = 3f,
                        cap = StrokeCap.Round

                    )

                })

                Image(
                    painter = painterResource(id = R.drawable.ic_unidirection),
                    contentDescription = null,
                    modifier = Modifier.constrainAs(directionIcon) {
                        top.linkTo(lineOne.bottom, 5.dp)
                        start.linkTo(parent.start, 15.dp)
                    }
                )

                Canvas(modifier = Modifier
                    .requiredWidth(10.dp)
                    .requiredHeight(100.dp)
                    .constrainAs(lineTwo) {
                        top.linkTo(directionIcon.bottom, 20.dp)
                        start.linkTo(parent.start, 27.dp)
                    }, onDraw = {

                    //line two
                    drawLine(
                        color = color,
                        start = Offset(2f, -20f),
                        end = Offset(2f, 190f),
                        strokeWidth = 3f,
                        cap = StrokeCap.Round

                    )

                })
            }

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(rightDeptcont) {
                        start.linkTo(leftdeptCont.end, 12.dp)
                        top.linkTo(parent.top, 25.dp)
                    }
                    .padding(
                        top = 15.dp,
                        bottom = 15.dp,
                        end = 40.dp
                    )
            ) {

                val (fromtxth, fromtxt, timefrotxt, totxth, totxt, timetotxt) = createRefs()

                val from = "CGK"
                val to = "NRT"

                Text(
                    text = "From ${from}",
                    style = TextStyle(
                        color = Color.LightGray,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.ceraprolight)),
                        fontWeight = FontWeight.Light
                    ),
                    modifier = Modifier.constrainAs(fromtxth) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                )

                Text(
                    text = "Jakarta",
                    style = TextStyle(
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 36.sp,
                        fontFamily = FontFamily(Font(R.font.ceraprobold)),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.constrainAs(fromtxt) {
                        top.linkTo(fromtxth.bottom, 3.dp)
                        start.linkTo(parent.start)
                    }
                )

                Text(
                    text = "21:25 ${"(${from})"}",
                    style = TextStyle(
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.ceraprolight)),
                        fontWeight = FontWeight.Light
                    ),
                    modifier = Modifier.constrainAs(timefrotxt) {
                        top.linkTo(parent.top, 16.dp)
                        end.linkTo(parent.end, 30.dp)
                    }
                )


                Text(
                    text = "To ${to}",
                    style = TextStyle(
                        color = Color.LightGray,
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.ceraprolight)),
                        fontWeight = FontWeight.Light
                    ),
                    modifier = Modifier.constrainAs(totxth) {
                        top.linkTo(fromtxt.bottom, 87.dp)
                        start.linkTo(parent.start)
                    }
                )

                Text(
                    text = "Tokyo",
                    style = TextStyle(
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 36.sp,
                        fontFamily = FontFamily(Font(R.font.ceraprobold)),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.constrainAs(totxt) {
                        top.linkTo(totxth.bottom, 5.dp)
                        start.linkTo(parent.start)
                    }
                )

                Text(
                    text = "06:45 ${"(${to})"}",
                    style = TextStyle(
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.ceraprolight)),
                        fontWeight = FontWeight.Light
                    ),
                    modifier = Modifier.constrainAs(timetotxt) {
                        top.linkTo(fromtxt.bottom, 115.dp)
                        end.linkTo(parent.end, 30.dp)
                    }
                )

            }

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(navContainer) {
                        top.linkTo(leftdeptCont.bottom, 25.dp)
                    }
                    .padding(start = 15.dp, end = 15.dp)
            ) {

                //refs
                val (oneBtn, roundBtn) = createRefs()

                //for knowing if the button one was clicked
                val isSelectedOne = remember {
                    mutableStateOf(true)
                }

                //for knowing if the button two was clicked
                val isSelectedTwo = remember {
                    mutableStateOf(false)
                }

                //one way button
                NavButton(
                    isSelected = isSelectedOne.value,
                    text = "One Way",
                    modifier = Modifier
                        .requiredHeight(34.dp)
                        .requiredWidth(100.dp)
                        .constrainAs(oneBtn) {
                            start.linkTo(parent.start)
                        }
                        .clickable {
                            //toggling button click
                            isSelectedOne.value = !isSelectedOne.value
                            isSelectedTwo.value = false
                        })

                //round trip button
                NavButton(
                    isSelected = isSelectedTwo.value,
                    text = "Round Trip",
                    modifier = Modifier
                        .requiredHeight(34.dp)
                        .requiredWidth(100.dp)
                        .constrainAs(roundBtn) {
                            end.linkTo(parent.end)
                        }
                        .clickable {
                            //toggling button click
                            isSelectedTwo.value = !isSelectedTwo.value
                            isSelectedOne.value = false
                        }
                )
            }

            ConstraintLayout(
                modifier = Modifier
                    .wrapContentSize()
                    .fillMaxWidth()
                    .constrainAs(deptArriveCont) {
                        top.linkTo(navContainer.bottom, 27.dp)
                    }
                    .padding(start = 15.dp, end = 15.dp)
            ) {

                val (depTxtH, deptxt, rettxtH, rettxt, calendarIcon) = createRefs()

                Text(text = "Depart",
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color.LightGray,
                        fontFamily = FontFamily(Font(R.font.cerapromedium)),
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.constrainAs(depTxtH) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                )

                Text(text = date,
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.ceraprobold)),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .constrainAs(deptxt) {
                            top.linkTo(depTxtH.bottom, 5.dp)
                            start.linkTo(parent.start)
                        }
                        .clickable {
                            openDatePicker("depart")
                        }
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_calendar),
                    contentDescription = null,
                    modifier = Modifier.constrainAs(calendarIcon) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, 13.dp)
                    })

                Text(text = "Return",
                    style = TextStyle(
                        fontSize = 15.sp,
                        color = Color.LightGray,
                        fontFamily = FontFamily(Font(R.font.cerapromedium)),
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.constrainAs(rettxtH) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                )

                Text(text = returnDate,
                    style = TextStyle(
                        fontSize = 22.sp,
                        color = Color.Black,
                        fontFamily = FontFamily(Font(R.font.ceraprobold)),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier
                        .constrainAs(rettxt) {
                            top.linkTo(rettxtH.bottom, 5.dp)
                            end.linkTo(parent.end)
                        }
                        .clickable {
                            openDatePicker("return")
                        }
                )

            }


            Text(text = "Go",
                style = TextStyle(color = Color.Red, fontSize = 36.sp),
                modifier = Modifier
                    .clickable {
                        checkDates()
                    }
                    .constrainAs(butontst) {
                        top.linkTo(deptArriveCont.bottom, 40.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })

        }

    }


}

private fun showDatePicker(
    activity: AppCompatActivity,
    updatedDate: (Long?) -> Unit
) {
    val picker = MaterialDatePicker.Builder.datePicker().build()
    picker.show(activity.supportFragmentManager, picker.toString())
    picker.addOnPositiveButtonClickListener {
        updatedDate(it)
    }
}
package com.zeroday.buupass.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.zeroday.buupass.R
import com.zeroday.buupass.model.Car

@Composable
fun CarCard(car: Car, section: String, height:Dp = 210.dp) {

    //val height = if (section.equals("available")) 230.dp else 210.dp
    val width = if (section.equals("available")) 170.dp else 190.dp

    val bottomPad = if (section.equals("available")) 12.dp else 5.dp

    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .requiredHeight(height)
            .requiredWidth(width)
            .padding(end = 12.dp, bottom = bottomPad),
        shape = MaterialTheme.shapes.small.copy(
            CornerSize(20.dp)
        )
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White)
                .padding(start = 15.dp, end = 15.dp)
        ) {

            val (img, namee, pricee, installment, monthtxt) = createRefs()

            car.method?.let { methood ->
                ConstraintLayout(
                    modifier = Modifier
                        .background(
                            color = Color(0xff8A8B9B), shape = MaterialTheme.shapes.small.copy(
                                CornerSize(12.dp)
                            )
                        )
                        .wrapContentSize(align = Alignment.Center)
                        .constrainAs(monthtxt) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top, 10.dp)
                        }
                ) {
                    Text(
                        text = methood,
                        style = TextStyle(
                            color = Color.White,
                            fontFamily = FontFamily(
                                Font(R.font.ubuntubold)
                            ),
                            fontSize = 12.sp
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                }

            }

            car.image?.let { imge ->

                val imgHeight = if (section.equals("available")) 70.dp else 110.dp

                Image(
                    painter = painterResource(id = imge),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .requiredHeight(110.dp)
                        .constrainAs(img) {
                            top.linkTo(parent.top, 17.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    contentScale = ContentScale.Crop
                )
            }

            car.name?.let { naame ->

                Text(
                    text = naame,
                    style = TextStyle(
                        fontSize = 17.sp,
                        color = colorResource(id = R.color.carmodelTxt), fontFamily = FontFamily(
                            Font(R.font.ubunturegular)
                        ),
                        fontWeight = FontWeight.Normal,
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier.constrainAs(namee) {
                        top.linkTo(img.bottom)
                        start.linkTo(parent.start)
                    }
                )
            }

            car.price?.let { priice ->

                Text(
                    text = "AED ${priice}",
                    style = TextStyle(
                        fontSize = 23.sp,
                        color = Color.Black, fontFamily = FontFamily(
                            Font(R.font.ubuntumedium)
                        ),
                        textAlign = TextAlign.Start
                    ),
                    modifier = Modifier.constrainAs(pricee) {
                        top.linkTo(namee.bottom, 4.dp)
                        start.linkTo(parent.start)
                    }
                )
            }

            car.method?.let { method ->

                if (section.equals("available")) {

                    if (method.equals("Monthly")) {
                        Text(
                            text = "Per month",
                            style = TextStyle(
                                color = Color.LightGray,
                                fontSize = 13.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.ubunturegular)
                                )
                            ),
                            modifier = Modifier.constrainAs(installment) {
                                top.linkTo(pricee.bottom, 10.dp)
                                start.linkTo(parent.start)
                            }
                        )
                    } else if (method.equals("Weekly")) {
                        Text(
                            text = "Per week",
                            style = TextStyle(
                                color = Color.LightGray,
                                fontSize = 13.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.ubunturegular)
                                )
                            ),
                            modifier = Modifier.constrainAs(installment) {
                                top.linkTo(pricee.bottom, 10.dp)
                                start.linkTo(parent.start)
                            }
                        )
                    } else if (method.equals("Daily")) {
                        Text(
                            text = "Per day",
                            style = TextStyle(
                                color = Color.LightGray,
                                fontSize = 13.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.ubunturegular)
                                )
                            ),
                            modifier = Modifier.constrainAs(installment) {
                                top.linkTo(pricee.bottom, 10.dp)
                                start.linkTo(parent.start)
                            }
                        )
                    }
                }
            }

        }

    }
}
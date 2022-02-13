package com.zeroday.buupass.components

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.zeroday.buupass.R
import com.zeroday.buupass.model.Car
import com.zeroday.buupass.util.DEFAULT_IMAGE
import com.zeroday.buupass.util.loadPicture
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun HomeScreen(dealsList: List<Car>, navController: NavController, imag: String) {

    ConstraintLayout(
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.gray))
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 60.dp)
                .background(
                    color = colorResource(id = R.color.gray),
                )
        ) {

            val (firstCont, availableCont, lastCont) = createRefs()

            ConstraintLayout(
                modifier = Modifier
                    .constrainAs(firstCont) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = MaterialTheme.shapes.small.copy(
                            bottomStart = CornerSize(33.dp),
                            bottomEnd = CornerSize(33.dp)
                        )
                    )
                    .wrapContentSize()
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 60.dp,
                            start = 25.dp,
                            end = 20.dp,
                            bottom = 32.dp
                        )
                ) {
                    val (avatar, text, img, modelText, manufactureText, garageText, plusIcon, arrowgar, avatarBadge) = createRefs()

                    val imagee = loadPicture(url = imag, defaultImage = DEFAULT_IMAGE).value

                    imagee?.let { img ->

                        CustomComponent(
                            modifier = Modifier
                                .size(58.dp)
                                .constrainAs(avatar) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                },
                            percentage = 0.85f,
                            color = colorResource(id = R.color.yellow),
                            strokeWidth = 3.dp,
                            img = img
                        )

                        val shapey = RoundedCornerShape(12.dp)

                        Row(horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clip(shapey)
                                .border(
                                    2.dp,
                                    Brush.linearGradient(
                                        listOf(
                                            Color.White,
                                            Color.White.copy(0.95f)
                                        ), start = Offset.Zero, end = Offset.Infinite
                                    ),
                                    shapey
                                )
                                .constrainAs(avatarBadge) {
                                    top.linkTo(parent.top, 30.dp)
                                    start.linkTo(parent.start, 45.dp)
                                }
                                .background(color = colorResource(id = R.color.yellow))) {
                            Text(
                                text = "Gold",
                                style = TextStyle(color = Color.White, fontSize = 14.sp, fontFamily = FontFamily(
                                    Font(R.font.ubuntumedium))),
                                modifier = Modifier.padding(
                                    top = 3.dp,
                                    bottom = 3.dp,
                                    start = 5.dp,
                                    end = 5.dp
                                )
                            )
                        }

                    }



                    Text(buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = colorResource(id = R.color.grayPricelab),
                                fontSize = 13.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.ubunturegular)
                                ),
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append("AED")
                        }

                        withStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontSize = 25.sp,
                                fontFamily = FontFamily(
                                    Font(R.font.ubuntumedium)
                                ),
                                fontWeight = FontWeight.Medium
                            )
                        ) {
                            append("2,400")
                        }
                    }, modifier = Modifier.constrainAs(text) {
                        top.linkTo(parent.top, 9.dp)
                        end.linkTo(plusIcon.start, 5.dp)
                    })

                    ConstraintLayout(
                        modifier = Modifier
                            .constrainAs(plusIcon) {
                                top.linkTo(parent.top, 7.dp)
                                end.linkTo(parent.end)
                            }
                            .background(
                                color = colorResource(id = R.color.blue),
                                shape = MaterialTheme.shapes.small.copy(
                                    CornerSize(6.dp)
                                )
                            )
                            .wrapContentSize()
                    ) {

                        val (plus) = createRefs()
                        Image(
                            painter = painterResource(id = R.drawable.ic_pluss),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(6.dp)
                                .constrainAs(plus) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(parent.top)
                                    bottom.linkTo(parent.bottom)
                                })

                    }

                    Image(
                        painter = painterResource(id = R.drawable.shelby),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(140.dp)
                            .constrainAs(img) {
                                top.linkTo(text.bottom, 15.dp)
                            },
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = "Shelby GT500",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = FontFamily(
                                Font(R.font.ubuntumedium)
                            )
                        ),
                        modifier = Modifier.constrainAs(modelText) {
                            top.linkTo(img.bottom, 9.dp)
                            start.linkTo(parent.start)
                        }
                    )

                    Text(
                        text = "Ford",
                        style = TextStyle(
                            color = colorResource(id = R.color.graymodelTxt),
                            fontSize = 14.sp,
                            fontFamily = FontFamily(
                                Font(R.font.ubunturegular)
                            )
                        ),
                        modifier = Modifier.constrainAs(manufactureText) {
                            top.linkTo(modelText.bottom, 5.dp)
                            start.linkTo(parent.start)
                        }
                    )

                    Text(
                        text = "My Garage",
                        style = TextStyle(
                            color = colorResource(id = R.color.bluetxt),
                            fontSize = 18.sp,
                            fontFamily = FontFamily(
                                Font(R.font.ubuntumedium)
                            ),
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.constrainAs(garageText) {
                            bottom.linkTo(parent.bottom, 8.dp)
                            end.linkTo(arrowgar.start, 7.dp)
                        }
                    )

                    Icon(
                        Icons.Filled.ArrowForward,
                        contentDescription = "Garage Arrow",
                        modifier = Modifier
                            .size(16.dp)
                            .constrainAs(arrowgar) {
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom, 7.dp)
                            },
                        tint = colorResource(id = R.color.blue)
                    )

                }


            }

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate(R.id.toavailableFragment)
                    }
                    .padding(
                        start = 14.dp,
                        end = 14.dp,
                    )
                    .constrainAs(availableCont) {
                        top.linkTo(firstCont.bottom, 29.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .background(
                        color = colorResource(id = R.color.blue),
                        shape = MaterialTheme.shapes.small.copy(CornerSize(20.dp))
                    )
                    .wrapContentSize()
            ) {

                ConstraintLayout(
                    Modifier
                        .fillMaxWidth()
                        .padding(22.dp)
                ) {

                    val (carTxt, longTxt, arrowcard) = createRefs()

                    Text(
                        text = "Available cars",
                        style = TextStyle(
                            color = Color.White, fontFamily = FontFamily(
                                Font(R.font.ubuntumedium)
                            ),
                            fontSize = 22.sp
                        ),
                        modifier = Modifier.constrainAs(carTxt) {
                            start.linkTo(parent.start)
                            top.linkTo(parent.top)
                        }

                    )

                    Text(
                        text = "Long term, Short term",
                        style = TextStyle(
                            color = Color.White.copy(0.7f), fontFamily = FontFamily(
                                Font(R.font.ubunturegular)
                            ),
                            fontSize = 17.sp
                        ),
                        modifier = Modifier.constrainAs(longTxt) {
                            start.linkTo(parent.start)
                            top.linkTo(carTxt.bottom, 4.dp)
                        }

                    )



                    CustomButton( modifier = Modifier
                        .constrainAs(arrowcard) {
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                        }.size(50.dp), color = colorResource(id = R.color.btnCircle), strokeWidth = 35.dp, percentage = 1f)

                }

            }

            ConstraintLayout(
                modifier = Modifier
                    .constrainAs(lastCont) {
                        top.linkTo(availableCont.bottom, 38.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .wrapContentSize()
                    .fillMaxWidth()
            ) {

                val (topTT, topMore, dealsLi, arrowmore) = createRefs()

                Text(
                    text = "TOP DEALS",
                    style = TextStyle(
                        color = Color.LightGray,
                        fontSize = 23.sp,
                        fontFamily = FontFamily(
                            Font(R.font.ubuntumedium)
                        ),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.constrainAs(topTT) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start, 20.dp)
                    }
                )

                Text(
                    text = "More",
                    style = TextStyle(
                        color = colorResource(id = R.color.bluetxt),
                        fontSize = 15.sp,
                        fontFamily = FontFamily(
                            Font(R.font.ubuntumedium)
                        ),
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.constrainAs(topMore) {
                        top.linkTo(parent.top, 4.dp)
                        end.linkTo(arrowmore.start, 6.dp)
                    }
                )

                Icon(
                    painterResource(id = R.drawable.ic_chevright),
                    contentDescription = "",
                    modifier = Modifier
                        .size(10.dp)
                        .constrainAs(arrowmore) {
                            top.linkTo(parent.top, 8.dp)
                            end.linkTo(parent.end, 20.dp)
                        },
                    tint = colorResource(id = R.color.blue)
                )

                LazyRow(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 9.dp)
                    .constrainAs(dealsLi) {
                        //top.linkTo(locationTitlee.bottom)
                        top.linkTo(topTT.bottom, 12.dp)
                        start.linkTo(parent.start)

                    }
                ) {
                    itemsIndexed(items = dealsList) { index, item ->
                        CarCard(car = item, section = "home")
                    }
                }
            }

        }
    }
}
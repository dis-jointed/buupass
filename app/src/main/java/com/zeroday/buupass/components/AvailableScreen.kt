package com.zeroday.buupass.components

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BadgeBox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.zeroday.buupass.R
import com.zeroday.buupass.model.Car

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun AvailableScreen(list: List<Car>, navController: NavController) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.gray))
    ) {

        val (bottomNavCont) = createRefs()


        ConstraintLayout(
            modifier = Modifier
                .background(color = Color.Transparent)
                .padding(
                    top = 65.dp,
                    start = 10.dp,
                    end = 7.dp
                )
        ) {

            val (backBtn, textTT, liss) = createRefs()

            ConstraintLayout(
                modifier = Modifier
                    .background(Color.Transparent)
                    .wrapContentSize()
                    .clickable {
                        navController.popBackStack()
                    }
                    .constrainAs(backBtn) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .border(
                        BorderStroke(
                            Dp.Hairline, brush = Brush.linearGradient(
                                colors = listOf(
                                    Color.Black, Color.Black
                                ), start = Offset.Zero, end = Offset.Infinite
                            )
                        ),
                        MaterialTheme.shapes.small.copy(CornerSize(28.dp))
                    )
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_chevleft),
                    contentDescription = "", modifier = Modifier.padding(17.dp)
                )

            }

            Text(
                text = "Available Cars", style = TextStyle(
                    color = Color.Black,
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.ubuntubold))
                ),
                modifier = Modifier.constrainAs(textTT) {
                    top.linkTo(backBtn.bottom, 30.dp)
                    start.linkTo(parent.start)
                }
            )

            LazyVerticalGrid(
                cells = GridCells.Fixed(2),
                modifier = Modifier.constrainAs(liss) {
                    top.linkTo(textTT.bottom, 15.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }.fillMaxHeight(0.69f),
                contentPadding = PaddingValues(1.dp),
                state = rememberLazyListState()
            ) {

                items(items = list) { item ->
                    CarCard(car = item, section = "available")
                }

            }


        }

        ConstraintLayout(
            modifier = Modifier
                .background(color = Color.White)
                .constrainAs(bottomNavCont) {
                    bottom.linkTo(parent.bottom)
                }
                .padding(bottom = 45.dp, start = 10.dp)) {

            val (bottomNav, bottomImg) = createRefs()

            val filterShapes = RoundedCornerShape(6.dp)

            ConstraintLayout(modifier = Modifier.constrainAs(bottomImg) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
            }) {

                val badgeTxt = createRef()


                ConstraintLayout(
                    modifier = Modifier
                        .clip(filterShapes)
                        .padding(
                            end = 2.dp,
                            bottom = 2.dp
                        )
                        .background(
                            colorResource(id = R.color.blue)
                        )
                ) {

                    val (filterImg) = createRefs()
                    Image(painter = painterResource(id = R.drawable.ic_filter),
                        contentDescription = "", modifier = Modifier
                            .padding(7.dp)
                            .constrainAs(filterImg) {
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            })

                }

                BadgeBox(
                    modifier = Modifier
                        .border(4.dp, color = Color.White, CircleShape)
                        .constrainAs(badgeTxt) {
                            bottom.linkTo(parent.bottom)
                            end.linkTo(parent.end)
                        },
                    badgeContent = {
                        Text(
                            text = "4",
                        )
                    }
                ) {

                }
                /*ConstraintLayout(

                ) {

                }*/


            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 62.dp)
                    .background(Color.White)
                    .requiredHeight(62.dp)
                    .constrainAs(bottomNav) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(bottomImg.end, 14.dp)
                    }
                    .horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {

                val selectedOneC: MutableState<Boolean> = remember {
                    mutableStateOf(true)
                }
                val selectedOne =
                    animateColorAsState(targetValue = if (selectedOneC.value) colorResource(id = R.color.blue) else Color.Gray)

                val selectedTwoC: MutableState<Boolean> = remember {
                    mutableStateOf(false)
                }
                val animatedColor =
                    animateColorAsState(targetValue = if (selectedTwoC.value) colorResource(id = R.color.blue) else Color.Gray)

                val selectedThreeC: MutableState<Boolean> = remember {
                    mutableStateOf(false)
                }
                val selectedThree =
                    animateColorAsState(targetValue = if (selectedThreeC.value) colorResource(id = R.color.blue) else Color.Gray)



                Text(
                    text = "Best Match",
                    modifier = Modifier.clickable {
                        selectedOneC.value = !selectedOneC.value
                        selectedTwoC.value = false
                        selectedThreeC.value = false
                    },
                    style = TextStyle(
                        color = selectedOne.value, fontSize = 18.sp, fontFamily = FontFamily(
                            Font(R.font.ubuntumedium)
                        )
                    )
                )

                Text(
                    text = "Highest Price",
                    modifier = Modifier.clickable {

                        selectedTwoC.value = !selectedTwoC.value
                        selectedOneC.value = false
                        selectedThreeC.value = false
                        Log.d("TAG", "AvailableScreen: ${selectedTwoC.value}")

                    },
                    style = TextStyle(
                        color = animatedColor.value,
                        fontSize = 18.sp,
                        fontFamily = FontFamily(
                            Font(R.font.ubuntumedium)
                        )
                    )
                )

                Text(
                    text = "Lowest Price",
                    modifier = Modifier
                        .clickable {
                            selectedThreeC.value = !selectedThreeC.value
                            selectedTwoC.value = false
                            selectedOneC.value = false
                        },
                    style = TextStyle(
                        color = selectedThree.value, fontSize = 18.sp, fontFamily = FontFamily(
                            Font(R.font.ubuntumedium)
                        )
                    )
                )

            }

        }
    }

}

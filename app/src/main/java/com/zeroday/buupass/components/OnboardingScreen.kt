package com.zeroday.buupass.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.Indicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material.icons.outlined.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.zeroday.buupass.R
import com.zeroday.buupass.model.OnboardingPage
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun OnboardingScreen(navController: NavController) {

    val scope = rememberCoroutineScope()

    val onboardPages = listOf(
        OnboardingPage(
            "We will\ntake care",
            "of tickets, transfers and a cool\nplace to stay",
            R.drawable.bag
        ),
        OnboardingPage(
            "Relax\n& enjoy",
            "Sunbathe, swim, eat and drink deliciously",
            R.drawable.sit
        ),
        OnboardingPage(
            "Flexible\npayment",
            "credit card and transfer,\ncryptocurrency",
            R.drawable.duck
        )
    )

    val onbaordingColor = remember {
        mutableStateOf(Color(0xFFF6C634))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(onbaordingColor.value)
            .padding(top = 50.dp, bottom = 60.dp, start = 38.dp, end = 20.dp)
    ) {

        topSection(navController = navController)

        val state = rememberPagerState(pageCount = onboardPages.size)

        HorizontalPager(
            state = state,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.8f)
        ) { page ->

            if (state.currentPage == 0) {

                onbaordingColor.value = colorResource(id = R.color.onboardcolorone)

            } else if (state.currentPage == 1) {

                onbaordingColor.value = colorResource(id = R.color.onboardcolortwo)

            } else if (state.currentPage == 2) {

                onbaordingColor.value = colorResource(id = R.color.onboardcolorthree)

            }

            OnBoardingItem(item = onboardPages[page])

        }

        bottomSection(
            size = onboardPages.size,
            index = state.currentPage,
            color = Color.Red,
            onNextClicked = {
                if (state.currentPage + 1 < onboardPages.size) {
                    scope.launch {
                        state.scrollToPage(state.currentPage + 1)
                    }
                } else {
                    navController.navigate(R.id.to_hoomeFragment)
                }
            })
    }


    /*@Composable
    fun BoxScope.Indicators(size : Int, index: Int){

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
                            if (isSelected) Color.Red
                            else Color.Black.copy(alpha = 0.5f)
                        )
                )
            }
        }
    }*/


}

@Composable
fun RowScope.Indicator(isSelected: Boolean) {


}


@Composable
fun topSection(navController: NavController) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        /* //back button
         IconButton(
             onClick = {},
             modifier = Modifier.align(Alignment.CenterStart)
         ) {
             Icon(Icons.Outlined.KeyboardArrowLeft, contentDescription = null)
         }*/

        //Skip button
        TextButton(
            onClick = { navController.navigate(R.id.to_hoomeFragment) },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Text(
                text = "Skip",
                color = Color.Black,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.ceraproregular)),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}

@Composable
fun bottomSection(
    size: Int,
    index: Int,
    onNextClicked: () -> Unit,
    color: Color
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {

        //indicators
        Indicators(size = size, index = index, color = color)

        //next button

        FloatingActionButton(
            onClick = { onNextClicked() },
            modifier = Modifier.align(Alignment.CenterEnd),
            contentColor = Color.Red,
            backgroundColor = Color.Black
        ) {
            Icon(Icons.Outlined.KeyboardArrowRight, null)
        }


    }
}

@Composable
fun OnBoardingItem(item: OnboardingPage) {
    Column(
        verticalArrangement = Arrangement.spacedBy(21.dp),
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = item.image),
            contentDescription = null,
            modifier = Modifier
                .size(280.dp)
                .align(Alignment.CenterHorizontally),
            //contentScale = ContentScale.Inside
        )

        Text(
            text = item.title,
            fontSize = 46.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.ceraprobold)),
            textAlign = TextAlign.Start
        )

        Text(
            text = item.description,
            fontSize = 19.sp,
            color = Color.Black,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Start,
            fontFamily = FontFamily(Font(R.font.cerapromedium)),
        )

    }
}
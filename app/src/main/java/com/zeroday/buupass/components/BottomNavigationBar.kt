package com.zeroday.haappyhour.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zeroday.buupass.R
import com.zeroday.buupass.model.BottomNavItem


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit,
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(modifier = modifier, backgroundColor = Color.Transparent) {

        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route

            val background = if (selected) colorResource(id = R.color.bluebottomback) else Color.White

            BottomNavigationItem(
                selected = selected,
                onClick = {
                    onItemClick(item)

                          },
                selectedContentColor = colorResource(id = R.color.bluebottom),
                unselectedContentColor = Color.LightGray,
                icon = {
                    ConstraintLayout(modifier = Modifier.wrapContentSize()) {

                        val (badgeBox, navContent) = createRefs()

                        if (item.badgeCount > 0) {
                            ConstraintLayout(modifier = Modifier
                                .clip(CircleShape)
                                .background(
                                    colorResource(id = R.color.blue)
                                )
                                .constrainAs(badgeBox) {
                                    top.linkTo(navContent.bottom, 2.dp)
                                    end.linkTo(parent.end)
                                }) {
                                Text(text = item.badgeCount.toString())
                            }


                        }
                        Row(
                            modifier = modifier
                                .background(
                                    background, shape = MaterialTheme.shapes.small.copy(
                                        CornerSize(19.dp)
                                    )
                                )
                                .wrapContentSize()
                                .constrainAs(navContent) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                                .padding(7.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {

                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name,
                            )

                            AnimatedVisibility(visible = selected) {
                                Text(
                                    text = item.name,
                                   fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.ubuntubold))
                                )
                            }

                        }

                    }
                }
            )


        }
    }

}
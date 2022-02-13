package com.zeroday.buupass.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.zeroday.buupass.R
import com.zeroday.buupass.model.BottomNavItem

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun CustomBotomNavigation(
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit,
) {

    val navShape = RoundedCornerShape(topStart = 22.dp, topEnd = 22.dp)
    val backStackEntry = navController.currentBackStackEntryAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(navShape)
            .background(color = Color.White),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route

            val background =
                if (selected) colorResource(id = R.color.bluebottomback) else Color.White

            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = colorResource(id = R.color.bluebottom),
                unselectedContentColor = Color.LightGray,
                icon = {
                    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {

                        val (badgeBox, navContent) = createRefs()

                        Row(
                            modifier = Modifier
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
                            ConstraintLayout() {

                                val (icon, bad) = createRefs()

                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name,
                                    modifier = Modifier.constrainAs(icon) {
                                        start.linkTo(parent.start)
                                    }
                                )
                                if (!selected.equals(true)) {
                                    if (item.badgeCount > 0) {
                                        BadgeBox(modifier = Modifier.constrainAs(bad) {
                                            top.linkTo(parent.bottom)
                                            end.linkTo(parent.end)
                                        },
                                            badgeContent = {
                                                Text(
                                                    text = "4",
                                                )
                                            }
                                        ) {

                                        }
                                    }
                                }
                            }


                            AnimatedVisibility(visible = selected) {
                                ConstraintLayout() {

                                    val (txt, badge) = createRefs()


                                    Text(
                                        modifier = Modifier.constrainAs(txt) {
                                            start.linkTo(parent.start)
                                        },
                                        text = item.name,
                                        fontSize = 16.sp,
                                        fontFamily = FontFamily(Font(R.font.ubuntubold))
                                    )

                                    if (item.badgeCount > 0) {
                                        BadgeBox(modifier = Modifier.constrainAs(badge) {
                                            top.linkTo(txt.bottom, 3.dp)
                                            end.linkTo(parent.end)
                                        },
                                            badgeContent = {
                                                Text(
                                                    text = "4",
                                                )
                                            }
                                        ) {

                                        }

                                    }
                                }

                            }

                        }

                    }
                }
            )


        }
    }

}
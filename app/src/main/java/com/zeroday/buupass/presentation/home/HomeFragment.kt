package com.zeroday.buupass.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.zeroday.buupass.R
import com.zeroday.buupass.components.BottomBarMain
import com.zeroday.buupass.components.CustomBotomNavigation
import com.zeroday.buupass.model.BottomNavItem
import com.zeroday.haappyhour.presentation.components.BottomNavigationBar

class HomeFragment : Fragment() {

    val viewModel: HomeViewModel by activityViewModels()


    val bottomListItems: List<BottomNavItem> = listOf(
        BottomNavItem("Home", "home", Icons.Default.Home),
        BottomNavItem("Setting", "settings", Icons.Default.Settings),
        BottomNavItem("Search", "search", Icons.Default.Search),
        BottomNavItem("Profile", "profile", Icons.Default.Person, 4),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getString("type").let { type ->
            if (type.equals("api")) {
                viewModel.getSingleUser()
            }else{
                viewModel.clearData()
            }
        }


    }


    @ExperimentalAnimationApi
    @ExperimentalMaterialApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {

                val navController = rememberNavController()
                val dealsList = viewModel.dealsList.value
                val image = viewModel.image.value


                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                        .padding(bottom = 40.dp)
                ) {
                    Scaffold(
                        bottomBar = {
                            CustomBotomNavigation(
                                items = bottomListItems,
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route){
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    ) {
                        BottomBarMain(
                            navController = navController,
                            dealsList = dealsList,
                            navControllerInit = findNavController(),
                            img = image
                        )
                    }
                }

            }


        }


    }


}
package com.zeroday.buupass.presentation.available

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.zeroday.buupass.components.AvailableScreen
import com.zeroday.buupass.presentation.home.HomeViewModel

class AvailableFragment : Fragment() {

    val viewModel: HomeViewModel by activityViewModels()

    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {

                val dealsList = viewModel.availabledealsList.value



                AvailableScreen(dealsList, navController = findNavController())
            }
        }
    }
}
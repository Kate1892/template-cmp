package com.template.cmp.navigation

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NavigationService {
    private var navController: NavHostController? = null
    private val _currentDestination = MutableStateFlow("")
    val currentDestination = _currentDestination.asStateFlow()

    private val onDestinationChangedListener =
        NavController.OnDestinationChangedListener { _, destination, _ ->
            _currentDestination.update {
                destination.route?.substringBefore("/") ?: ""
            }
        }

    fun setNavController(navController: NavHostController) {
        this.navController = navController
        navController.removeOnDestinationChangedListener(onDestinationChangedListener)
        navController.addOnDestinationChangedListener(onDestinationChangedListener)
    }

    fun navigate(T: Any) {
        navController?.run {
            navigate(T)
        }
    }
}
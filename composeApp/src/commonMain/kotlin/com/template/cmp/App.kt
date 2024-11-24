package com.template.cmp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.template.cmp.features.common.compose.StatusBarLight
import com.template.cmp.navigation.NavigationService
import com.template.cmp.theme.NoteTheme
import navigation.NavigationGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.mp.KoinPlatformTools


@Preview
@Composable
fun App() {
    val navigationService
            by KoinPlatformTools.defaultContext().get().inject<NavigationService>()

    NoteTheme {
        val navController: NavHostController = rememberNavController()
        navigationService.setNavController(navController)

        StatusBarLight(true)
        NavigationGraph(navController = navController)
    }
}

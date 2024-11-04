package navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.template.cmp.navigation.NavigationAction
import com.template.cmp.navigation.initRoutes
import com.template.cmp.theme.NoteTheme

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationAction.NavigateToSplash,
        enterTransition = { fadeIn() },
        exitTransition = { fadeOut() },
        modifier = Modifier
            .fillMaxSize()
            .background(color = NoteTheme.colors.screenBackground)
    ) {
        initRoutes()
    }
}
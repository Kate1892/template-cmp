package com.template.cmp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.template.cmp.theme.NoteTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            // TODO
//            MainActivityView()
            NoteTheme {
//                NavGraph(navController = navController)
                App(
//                    client = remember {
//                        InsultCensorClient(createHttpClient(OkHttp.create()))
//                    }
                )
            }
        }
    }

    @Composable
    private fun MainActivityView(
        navController: NavHostController = rememberNavController(),
    ) {

    }
}

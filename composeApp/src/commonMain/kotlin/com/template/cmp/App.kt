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

//@Composable
//@Preview
//fun App(
//    client: InsultCensorClient,
//) {
//     val navigationService: NavigationService by inject()
//
//    val navController: NavHostController = rememberNavController()
//
//    MaterialTheme {
//        NavGraph(navController = navController)
//
////        MaterialTheme {
////            var censoredText by remember {
////                mutableStateOf<String?>(null)
////            }
////            var uncensoredText by remember {
////                mutableStateOf("")
////            }
////            var isLoading by remember {
////                mutableStateOf(false)
////            }
////            var errorMessage by remember {
////                mutableStateOf<NetworkError?>(null)
////            }
////            val scope = rememberCoroutineScope()
////            Column(
////                modifier = Modifier
////                    .fillMaxSize(),
////                horizontalAlignment = Alignment.CenterHorizontally,
////                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
////            ) {
////                TextField(
////                    value = uncensoredText,
////                    onValueChange = { uncensoredText = it },
////                    modifier = Modifier
////                        .padding(horizontal = 16.dp)
////                        .fillMaxWidth(),
////                    placeholder = {
////                        Text("Uncensored text")
////                    }
////                )
////                Button(onClick = {
////                    scope.launch {
////                        isLoading = true
////                        errorMessage = null
////
////                        client.censorWords(uncensoredText)
////                            .onSuccess {
////                                censoredText = it
////                            }
////                            .onError {
////                                errorMessage = it
////                            }
////                        isLoading = false
////                    }
////                }) {
////                    if (isLoading) {
////                        CircularProgressIndicator(
////                            modifier = Modifier
////                                .size(15.dp),
////                            strokeWidth = 1.dp,
////                            color = Color.White
////                        )
////                    } else {
////                        Text("Censor!")
////                    }
////                }
////                censoredText?.let {
////                    Text(it)
////                }
////                errorMessage?.let {
////                    Text(
////                        text = it.name,
////                        color = Color.Red
////                    )
////                }
////            }
////        }
//    }
//}
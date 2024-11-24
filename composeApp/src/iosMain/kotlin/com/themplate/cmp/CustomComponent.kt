package com.themplate.cmp

/** important - not working because of file name */
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.interop.UIKitView
//import kotlinx.cinterop.ExperimentalForeignApi
//import kotlinx.cinterop.ObjCAction
//import platform.UIKit.UIButton
//import platform.UIKit.UIControlEventTouchUpInside
//import platform.UIKit.UIButtonTypeSystem
//import platform.darwin.sel_registerName
//
//@OptIn(ExperimentalForeignApi::class)
//@Composable
//fun CustomButton(label: String, onClick: () -> Unit) {
//    UIKitView(
//        modifier = Modifier,
//        factory = {
//            // Create a UIButton instance
//            val uiButton = UIButton.buttonWithType(UIButtonTypeSystem)
//            uiButton.setTitle(label, forState = 0u)
//
//            // Set up action for button click
//            val block = object : NSObject() {
//                @ObjCAction
//                fun buttonClicked() {
//                    onClick() // Call the provided onClick lambda
//                }
//            }
//
//            uiButton.addTarget(
//                block,
//                action = sel_registerName("buttonClicked"),
//                forControlEvents = UIControlEventTouchUpInside
//            )
//
//            uiButton // Return UIButton instance
//        },
//        update = { uiButton ->
//            // Update UI state if needed (e.g., change title dynamically)
//            uiButton.setTitle(label, forState = 0u)
//        }
//    )
//}
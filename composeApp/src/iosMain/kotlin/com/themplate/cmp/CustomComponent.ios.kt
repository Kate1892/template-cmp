package com.themplate.cmp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import platform.UIKit.UIButton
import platform.UIKit.UIButtonTypeSystem
import platform.UIKit.UIControlEventTouchUpInside
import platform.darwin.sel_registerName

@OptIn(ExperimentalForeignApi::class)
@Composable
fun CustomButton(label: String, onClick: () -> Unit) {
    UIKitView(
        modifier = Modifier,
        factory = {
            val uiButton = UIButton.buttonWithType(UIButtonTypeSystem)
            uiButton.setTitle(label, forState = 0u)

            val block = object : NSObject() {
                @ObjCAction
                fun buttonClicked() {
                    onClick()
                }
            }

            uiButton.addTarget(
                block,
                action = sel_registerName("buttonClicked"),
                forControlEvents = UIControlEventTouchUpInside
            )

            uiButton
        },
        update = { uiButton ->
            /** Update UI state if needed (e.g., change title dynamically) */
            uiButton.setTitle(label, forState = 0u)
        }
    )
}
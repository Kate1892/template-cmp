package com.template.cmp.common.view

data class TextFieldState(
    val title: String = "",
    val subTitle: String = "",
    val placeholder: String = "",
    val enteredValue: String = "",
    val isFocused: Boolean = false,
    val isEnabled: Boolean = true,
)

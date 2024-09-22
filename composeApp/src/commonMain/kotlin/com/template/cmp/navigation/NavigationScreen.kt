package com.template.cmp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationScreen {
    @Serializable
    data object NoteList : NavigationScreen()

    @Serializable
    internal data class Profile(
        val name: String = "",
        val age: Int = 4,
    ) : NavigationScreen()
}

//data class NavigateToRepeatCreatePin(
//    val name: String?,
//    val age: Int,
//) : NavigationAction()

package com.template.cmp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavigationAction(val fromRoot: Boolean = false) {
    @Serializable
    data object NavigateBack : NavigationAction()

    @Serializable
    data object NavigateToSplash : NavigationAction(fromRoot = true)

    @Serializable
    data object NavigateToLogin : NavigationAction(fromRoot = true)

    @Serializable
    data object NavigateToNotes : NavigationAction()

    @Serializable
    data object NavigateToCreateNote : NavigationAction()

    @Serializable
    data object NavigateToCheckPermission : NavigationAction()

    @Serializable
    class NavigateBackToLogin(
        val targetRoute: NavigateToLogin = NavigateToLogin,
    ) : NavigationAction()
}
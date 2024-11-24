package com.template.cmp.navigation

import androidx.navigation.NavGraphBuilder
import com.template.cmp.features.auth.login.navRouteLogin
import com.template.cmp.features.checkPermission.navRouteCheckPermission
import com.template.cmp.features.note.notes.navRouteNotes
import com.template.cmp.features.splash.navRouteSplash


fun NavGraphBuilder.initRoutes() {
    navRouteSplash()
    navRouteLogin()
    navRouteNotes()
    navRouteCheckPermission()
}
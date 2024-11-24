package com.template.cmp.features.checkPermission.mvvm

import dev.icerock.moko.permissions.PermissionState

data class CheckPermissionState(
    val permissionGrantedTitle: String = "",
    val permissionTwiceDeniedTitle: String = "",
    val requestPermissionButtonTitle: String = "",
    val grantInSettingButtonTitle: String = "",
    val continueButtonTitle: String = "",
    val permissionStatus: PermissionState = PermissionState.NotDetermined,
)
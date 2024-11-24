package com.template.cmp.features.checkPermission.mvvm

import androidx.lifecycle.viewModelScope
import com.template.cmp.common.mvvm.BaseViewModel
import com.template.cmp.navigation.NavigationAction
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString
import template_cmp.composeapp.generated.resources.Res
import template_cmp.composeapp.generated.resources.scr_permission_continue_button_title
import template_cmp.composeapp.generated.resources.scr_permission_open_settings_button_title
import template_cmp.composeapp.generated.resources.scr_permission_permission_denied_title
import template_cmp.composeapp.generated.resources.scr_permission_permission_granted_title
import template_cmp.composeapp.generated.resources.scr_permission_request_button_title

private val AUDIO_PERMISSION = Permission.RECORD_AUDIO

class CheckPermissionViewModel(
    private val controller: PermissionsController,
) : BaseViewModel<CheckPermissionUiEvent, CheckPermissionState>(CheckPermissionState()) {


    override fun processUiEvent(event: CheckPermissionUiEvent) {
        when (event) {
            CheckPermissionUiEvent.OnGoToSettingsButtonClicked -> {
                controller.openAppSettings()
            }

            CheckPermissionUiEvent.OnRequestPermissionButtonClicked -> {
                provideOrRequestRecordAudioPermission()
            }

            CheckPermissionUiEvent.OnScreenDrawn -> checkPermission()
            CheckPermissionUiEvent.OnContinueButtonClicked -> navigate(NavigationAction.NavigateToNotes)
        }
    }

    /** does not work as expected because of reason bellow */
    /** without that - ok, but if granted in settings - update only after relaunching*/
    override fun onScreenResumed() {
        super.onScreenResumed()

        viewModelScope.launch {
            val status = controller.getPermissionState(AUDIO_PERMISSION)

            if (status == PermissionState.Granted)
                updateState {
                    copy(
                        permissionStatus = status
                    )
                }
        }
    }


    /** after 2 denies and relaunching - it is just denied, not denied always
    right after second deny - it is denied always */
    private fun checkPermission() {
        viewModelScope.launch {
            val status = controller.getPermissionState(AUDIO_PERMISSION)

            updateState {
                copy(
                    permissionStatus = status
                )
            }
        }
    }

    private fun provideOrRequestRecordAudioPermission() {
        viewModelScope.launch {
            var status = PermissionState.Denied
            try {
                controller.providePermission(AUDIO_PERMISSION)
                status = PermissionState.Granted
            } catch (e: DeniedAlwaysException) {
                status = PermissionState.DeniedAlways
            } catch (e: DeniedException) {
                status = PermissionState.Denied
            } catch (e: RequestCanceledException) {
                e.printStackTrace()
            }

            updateState {
                copy(
                    permissionStatus = status
                )
            }
        }
    }

    override fun initScreenStrings(): suspend CheckPermissionState.() -> CheckPermissionState = {
        copy(
            permissionGrantedTitle = getString(
                Res.string.scr_permission_permission_granted_title
            ),
            permissionTwiceDeniedTitle = getString(
                Res.string.scr_permission_permission_denied_title
            ),
            requestPermissionButtonTitle = getString(
                Res.string.scr_permission_request_button_title
            ),
            grantInSettingButtonTitle = getString(
                Res.string.scr_permission_open_settings_button_title
            ),
            continueButtonTitle = getString(
                Res.string.scr_permission_continue_button_title
            ),
        )
    }
}
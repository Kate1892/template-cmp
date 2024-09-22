package com.template.cmp.features.device

import com.template.cmp.common.mvvm.SingleClickUiEvent
import com.template.cmp.common.mvvm.UiEvent
import kotlinx.datetime.Clock

class SingleClickService {
    private val singleClickTimeout = 500L
    private var lastClickTime: Long = 0

    fun isClickAllowed(event: UiEvent): Boolean {
        return when {
            event !is SingleClickUiEvent -> true
            Clock.System.now().toEpochMilliseconds() - lastClickTime < singleClickTimeout -> false
            else -> {
                lastClickTime = Clock.System.now().toEpochMilliseconds()
                true
            }
        }
    }

    fun isHardwareBackClickAllowed(): Boolean {
        return (
                Clock.System.now()
                    .toEpochMilliseconds() - lastClickTime >= singleClickTimeout
                ).also { allowed ->
                if (allowed) lastClickTime = Clock.System.now().toEpochMilliseconds()
            }
    }
}

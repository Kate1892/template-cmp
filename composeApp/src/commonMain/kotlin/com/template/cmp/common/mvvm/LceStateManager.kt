package com.template.cmp.common.mvvm

import com.template.cmp.di.DEFAULT_DISPATCHER_NAME
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.core.qualifier.named
import org.koin.mp.KoinPlatformTools

private const val NO_INTERNET_ERROR_TIMEOUT = 15 // sec

class LceStateManager(
//    private val connectivityService: ConnectivityService,
//    private val deviceService: DeviceService,

) {
    private val localScope = CoroutineScope(
        KoinPlatformTools.defaultContext().get().get<CoroutineDispatcher>(
            named(DEFAULT_DISPATCHER_NAME)
        )
    )

    private var noInternetReconnectJob: Job? = null
    private var noInternetReconnectAttemptTime = NO_INTERNET_ERROR_TIMEOUT
    private var loadScreenDataAction: () -> Unit = {}
    private var uiLifecycleSubscriptionJob: Job? = null

    private val _lceState = MutableStateFlow(LceState())
    val lceState = _lceState.asStateFlow()

    private fun updateState(transform: LceState.() -> LceState) {
        _lceState.update { transform.invoke(lceState.value) }
    }

    fun subscribeDataWithUiLifecycle() {
        loadScreenDataAction()
//        uiLifecycleSubscriptionJob?.cancel()
//        uiLifecycleSubscriptionJob = connectivityService.isInternetConnectionAvailable.onEach { isConnected ->
//            when (isConnected) {
//                false -> {
//                    if (!isNoInternetErrorShowing()) {
//                        showNoInternetError()
//                    }
//                }
//
//                true -> {
//                    if (isNoInternetErrorShowing() && !isLoading()) {
//                        loadScreenDataAction()
//                    }
//                }
//            }
//        }.launchIn(localScope)
    }

    fun unsubscribeDataWithUiLifecycle() {
        uiLifecycleSubscriptionJob?.cancel()
    }

    fun initializeLoadScreenData(
        loadScreenData: () -> Unit,
    ) {
        loadScreenDataAction = loadScreenData
    }

    fun showLoading() {
        updateState { copy(isLoading = true) }
    }

    fun hideLoading() {
        updateState { copy(isLoading = false) }
    }


}
package com.template.cmp.common.mvvm

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.template.cmp.features.device.SingleClickService
import com.template.cmp.navigation.NavigationAction
import com.template.cmp.navigation.NavigationService
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.mp.KoinPlatformTools

@Stable
interface UiEventReducer<TUiEvent : UiEvent> {
    fun onUiEvent(event: TUiEvent)
}

abstract class BaseViewModel<
        TUiEvent : UiEvent,
        TState : Any,
        >(initialState: TState) : ViewModel(), UiEventReducer<TUiEvent> {

    private val navigationService
            by KoinPlatformTools.defaultContext().get().inject<NavigationService>()

    private val singleClickService
            by KoinPlatformTools.defaultContext().get().inject<SingleClickService>()

    protected val lceStateManager
            by KoinPlatformTools.defaultContext().get().inject<LceStateManager>()


    private var isScreenDataInitialized = false
    private var uiLifecycleSubscriptionJob = mutableListOf<Job>()

    /** State */
    @Suppress("RemoveExplicitTypeArguments")
    private val _stateFlow =
        MutableStateFlow<ScreenState<TState>>(ScreenState(state = initialState))
    val stateFlow get() = _stateFlow.asStateFlow()
    val state: TState get() = stateFlow.value.state

    protected fun updateState(transform: TState.() -> TState) {
        _stateFlow.update { stateFlow.value.copy(state = transform.invoke(state)) }
    }

    protected fun updateStateSuspend(transform: suspend TState.() -> TState) {
        viewModelScope.launch {
            _stateFlow.update { stateFlow.value.copy(state = transform.invoke(state)) }
        }
    }

    protected open fun initScreenStrings(): suspend TState.() -> TState {
        return { state }
    }

    private fun subscribeDataWithUiLifecycle() {
        uiLifecycleSubscriptionJob.forEach { it.cancel() }
        uiLifecycleSubscriptionJob.add(
            lceStateManager.lceState.onEach { lceState ->
                _stateFlow.update { stateFlow.value.copy(lceState = lceState) }
            }.launchIn(viewModelScope)
        )

        lceStateManager.subscribeDataWithUiLifecycle()
    }

    private fun unsubscribeDataWithUiLifecycle() {
        lceStateManager.unsubscribeDataWithUiLifecycle()
        uiLifecycleSubscriptionJob.forEach { it.cancel() }
    }

    private fun initializeScreenData() {
        initScreenStringsInternal()
        loadScreenDataInternal()
        lceStateManager.initializeLoadScreenData(::loadScreenDataInternal)
    }

    private fun initScreenStringsInternal() {
        viewModelScope.launch {
            _stateFlow.update { stateFlow.value.copy(state = initScreenStrings().invoke(state)) }
        }
    }


    private fun loadScreenDataInternal() {
        viewModelScope.launch {
            showLoading()
        }
    }

    protected open fun showLoading() {
        lceStateManager.showLoading()
    }

    protected open fun hideLoading() {
        lceStateManager.hideLoading()
    }


    /** UI events */
    override fun onUiEvent(event: TUiEvent) {
        if (!singleClickService.isClickAllowed(event)) {
            return
        }

        processUiEvent(event)
    }

    protected abstract fun processUiEvent(event: TUiEvent)

    protected fun navigate(navigationAction: NavigationAction) {
        navigationService.navigate(navigationAction)
    }

    override fun onDefaultUiEvent(event: DefaultUiEvent) {
        if (!singleClickService.isClickAllowed(event)) {
            return
        }

        when (event) {
            DefaultUiEvent.OnScreenCreated -> {
                subscribeDataWithUiLifecycle()
                if (!isScreenDataInitialized) {
                    isScreenDataInitialized = true
                    initializeScreenData()
                }
                onScreenCreated()
            }

            DefaultUiEvent.OnScreenResumed -> onScreenResumed()
            DefaultUiEvent.OnScreenDestroyed -> {
                unsubscribeDataWithUiLifecycle()
                onScreenDestroyed()
            }

            DefaultUiEvent.OnAlertClosedUiEvent -> onAlertClosed()
            DefaultUiEvent.OnBottomSheetClosedUiEvent -> onBottomSheetClosed()
            DefaultUiEvent.OnDatePickerClosedUiEvent -> onDatePickerClosed()
            DefaultUiEvent.OnBackClicked -> processBackClicked()
            DefaultUiEvent.OnNotificationsClicked -> navigate(NavigationAction.NavigateToNotifications)
        }
    }


//    protected open fun processBackClicked() {
//        navigateBack()
//    }
//
//    protected fun navigateBack() {
//        navigationService.navigateBack()
//    }
}
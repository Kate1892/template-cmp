package com.template.cmp.common.mvvm

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import com.template.cmp.features.device.SingleClickService
import com.template.cmp.navigation.NavigationService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.koin.mp.KoinPlatformTools

@Stable
interface UiEventReducer<TUiEvent : UiEvent> {
    fun onUiEvent(event: TUiEvent)
}

abstract class BaseViewModel<
        TUiEvent : UiEvent,
        TState : Any,
        >(initialState: TState) : ViewModel(), UiEventReducer<TUiEvent> {
    private val singleClickService
            by KoinPlatformTools.defaultContext().get().inject<SingleClickService>()
    private val navigationService
            by KoinPlatformTools.defaultContext().get().inject<NavigationService>()

    /** State */
    @Suppress("RemoveExplicitTypeArguments")
    private val _stateFlow =
        MutableStateFlow<ScreenState<TState>>(ScreenState(state = initialState))
    val stateFlow get() = _stateFlow.asStateFlow()
    val state: TState get() = stateFlow.value.state

    protected fun updateState(transform: TState.() -> TState) {
        _stateFlow.update { stateFlow.value.copy(state = transform.invoke(state)) }
    }

    /** UI events */
    override fun onUiEvent(event: TUiEvent) {
        if (!singleClickService.isClickAllowed(event)) {
            return
        }

        processUiEvent(event)
    }

    protected abstract fun processUiEvent(event: TUiEvent)

    protected fun navigate(T: Any) {
        navigationService.navigate(T)
    }

//    protected fun navigateBack() {
//        navigationService.navigateBack()
//    }
}
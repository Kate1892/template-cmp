package com.template.cmp.network

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import org.koin.core.component.KoinComponent
import org.koin.mp.KoinPlatformTools

class NetworkService : KoinComponent {

    private val localScope = CoroutineScope(
        KoinPlatformTools.defaultContext().get().get<CoroutineDispatcher>()
    )

    init {

    }

//    fun initNetwork() {
//        NetworkData.initNetwork(
//            isProduction = isProduction,
//            isLoggingEnabled = isLoggingEnabled,
//            deviceLanguage = deviceLanguage,
//        )
//    }
}
package com.template.cmp.di

import com.template.cmp.common.mvvm.LceStateManager
import com.template.cmp.core.network.auth.AuthApi
import com.template.cmp.core.network.common.api.ApiHttpClient
import com.template.cmp.core.network.common.api.request.GetRequest
import com.template.cmp.core.network.common.api.request.Interceptor
import com.template.cmp.core.network.common.api.request.PostRequest
import com.template.cmp.dependencies.MyRepository
import com.template.cmp.dependencies.MyRepositoryImpl
import com.template.cmp.dependencies.MyViewModel
import com.template.cmp.features.auth.login.mvvm.LoginViewModel
import com.template.cmp.features.device.SingleClickService
import com.template.cmp.features.note.notes.mvvm.NotesViewModel
import com.template.cmp.features.splash.mvvm.SplashViewModel
import com.template.cmp.navigation.NavigationService
import com.template.cmp.network.features.auth.AuthRepository
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

const val DEFAULT_DISPATCHER_NAME = "defaultDispatcher"

expect val platformModule: Module

val sharedModule = module {
    single(named(DEFAULT_DISPATCHER_NAME)) { Dispatchers.Default }

    singleOf(::MyRepositoryImpl).bind<MyRepository>()
    viewModelOf(::MyViewModel)

    viewModelOf(::NotesViewModel)
    viewModelOf(::SplashViewModel)
    viewModelOf(::LoginViewModel)

    singleOf(::NavigationService)
    singleOf(::SingleClickService)

    factoryOf(::LceStateManager)

    singleOf(::ApiHttpClient)
    singleOf(::PostRequest)
    singleOf(::GetRequest)
    singleOf(::Interceptor)

    singleOf(::AuthApi)
    singleOf(::AuthRepository)

}
package com.template.cmp.di

import com.template.cmp.dependencies.MyRepository
import com.template.cmp.dependencies.MyRepositoryImpl
import com.template.cmp.dependencies.MyViewModel
import com.template.cmp.features.device.SingleClickService
import com.template.cmp.features.task.notes.mvvm.NotesViewModel
import com.template.cmp.navigation.NavigationService
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    singleOf(::MyRepositoryImpl).bind<MyRepository>()
    viewModelOf(::MyViewModel)
    viewModelOf(::NotesViewModel)

    singleOf(::NavigationService)
    singleOf(::SingleClickService)

}
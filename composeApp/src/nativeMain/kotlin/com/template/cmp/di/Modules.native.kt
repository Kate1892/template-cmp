package com.template.cmp.di

import com.template.cmp.dependencies.DbClient
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val platformModule: Module
    get() = module {
        singleOf(::DbClient)

    }
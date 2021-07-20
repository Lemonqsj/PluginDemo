package com.lemon.lib_base.di

import com.lemon.lib_base.base.AppViewModelFactory
import com.lemon.lib_base.base.MyApplication
import com.lemon.lib_base.data.DataRespository
import com.lemon.lib_base.data.api.ApiService
import com.lemon.lib_base.data.net.RetrofitClient
import com.lemon.lib_base.data.source.HttpDataSource
import com.lemon.lib_base.data.source.LocalDataSource
import com.lemon.lib_base.data.source.impl.HttpDataImpl
import com.lemon.lib_base.data.source.impl.LocalDataImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single { androidApplication() as MyApplication }

    single { RetrofitClient.getInstance().create(ApiService::class.java) }

    single<HttpDataSource> { HttpDataImpl(get()) }

    single<LocalDataSource> { LocalDataImpl() }

    single { AppViewModelFactory(get(), get()) }
    single { DataRespository(get(), get()) }

}

val factoryModule = module { }

val allModule = appModule + factoryModule
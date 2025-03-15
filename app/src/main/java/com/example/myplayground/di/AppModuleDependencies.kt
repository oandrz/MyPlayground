package com.example.myplayground.di

import com.example.core.networking.MyPlaygroundNetworkImpl
import com.example.core_api.MyPlaygroundNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppModuleDependencies {

    @Singleton
    @Binds
    fun bindMyPlaygroundNetwork(impl: MyPlaygroundNetworkImpl): MyPlaygroundNetwork
}
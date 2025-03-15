package com.example.list.di

import com.example.core_api.MyPlaygroundNetwork
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface ListModuleDependencies {
    fun myPlaygroundNetwork(): MyPlaygroundNetwork
}
package com.example.list.di

import com.example.list.data.ListScreenRepository
import com.example.list.data.ListScreenRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface ListModules {
    @Binds
    @Singleton
    fun bindListScreenRepository(impl: ListScreenRepositoryImpl): ListScreenRepository
}
package com.example.educational3dworld.di

import com.example.educational3dworld.domain.repository.AppRepository
import com.example.educational3dworld.domain.repository.impl.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun getAppRepository(impl: AppRepositoryImpl): AppRepository

}
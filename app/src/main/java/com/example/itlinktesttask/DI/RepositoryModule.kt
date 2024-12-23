package com.example.itlinktesttask.DI

import com.example.itlinktesttask.domain.Repository
import com.example.itlinktesttask.domain.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideRepository(impl:RepositoryImpl): Repository
}
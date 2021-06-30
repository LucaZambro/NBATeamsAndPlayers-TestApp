package com.example.nbateamsandplayers_testapp.di

import com.example.app_data.repository.PlayerRepositoryImpl
import com.example.app_domain.repository.PlayerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PlayerRepositoryModule {

    @Binds
    @Singleton
    abstract fun providePlayerRepository(repo: PlayerRepositoryImpl): PlayerRepository

}
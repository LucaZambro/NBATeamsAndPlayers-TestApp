package com.example.nbateamsandplayers_testapp.di

import com.example.app_data.repository.TeamRepositoryImpl
import com.example.app_domain.repository.TeamRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class TeamRepositoryModule {

    @Binds
    @Singleton
    abstract fun provideTeamRepository(repo: TeamRepositoryImpl): TeamRepository

}
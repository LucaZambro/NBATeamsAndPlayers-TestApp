package com.example.nbateamsandplayers_testapp.di

import com.example.app_data.network.NetworkDatasource
import com.example.nbateamsandplayers_testapp.framework.network.NetworkDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkModule {

    @Binds
    @Singleton
    abstract fun provideNetworkDataSource(ds: NetworkDatasourceImpl): NetworkDatasource

}
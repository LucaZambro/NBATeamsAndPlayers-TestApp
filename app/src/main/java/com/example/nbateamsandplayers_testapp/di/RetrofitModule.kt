package com.example.nbateamsandplayers_testapp.di

import com.example.nbateamsandplayers_testapp.framework.network.APIInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofit(): APIInterface {
//        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader(
                        "x-rapidapi-key",
                        "c103ef71aamshab690731e89a82ep1bf329jsn05f5336d78cf"
                    )
                    .addHeader("x-rapidapi-host", "free-nba.p.rapidapi.com")
                    .addHeader("useQueryString", "true")
                    .build()
                chain.proceed(newRequest)
            }.build()

        return Retrofit.Builder()
            .baseUrl("https://free-nba.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(APIInterface::class.java)
    }
}

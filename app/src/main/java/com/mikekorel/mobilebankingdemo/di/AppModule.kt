package com.mikekorel.mobilebankingdemo.di

import com.mikekorel.mobilebankingdemo.core.Constants
import com.mikekorel.mobilebankingdemo.data.remote.Api
import com.mikekorel.mobilebankingdemo.data.repository.RepositoryImpl
import com.mikekorel.mobilebankingdemo.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): Api {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: Api): Repository {
        return RepositoryImpl(api)
    }
}
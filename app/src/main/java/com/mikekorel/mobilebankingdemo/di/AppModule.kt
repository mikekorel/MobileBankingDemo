package com.mikekorel.mobilebankingdemo.di

import com.mikekorel.mobilebankingdemo.BuildConfig
import com.mikekorel.mobilebankingdemo.core.Constants
import com.mikekorel.mobilebankingdemo.data.remote.Api
import com.mikekorel.mobilebankingdemo.data.remote.BasicAuthInterceptor
import com.mikekorel.mobilebankingdemo.data.repository.RepositoryImpl
import com.mikekorel.mobilebankingdemo.domain.repository.Repository
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
object AppModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor())

        if (BuildConfig.DEBUG) {
            client.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return client.build()
    }

    @Provides
    @Singleton
    fun provideApi(client: OkHttpClient): Api {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
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
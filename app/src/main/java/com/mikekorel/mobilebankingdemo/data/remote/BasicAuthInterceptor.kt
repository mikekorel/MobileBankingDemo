package com.mikekorel.mobilebankingdemo.data.remote

import com.mikekorel.mobilebankingdemo.core.Constants
import com.mikekorel.mobilebankingdemo.core.Constants.CREDENTIALS_PASSWORD
import com.mikekorel.mobilebankingdemo.core.Constants.CREDENTIALS_USERNAME
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor : Interceptor {
    private val credentials = Credentials.basic(CREDENTIALS_USERNAME, CREDENTIALS_PASSWORD)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder().header("Authorization", credentials).build()
        return chain.proceed(request)
    }
}
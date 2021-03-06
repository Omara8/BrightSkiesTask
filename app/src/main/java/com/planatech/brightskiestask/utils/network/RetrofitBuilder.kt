package com.planatech.brightskiestask.utils.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder {

    companion object {

        private var defaultHeaders: MutableMap<String, String> =
            mutableMapOf(Pair("Content-Type", "application/json"))
        var loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        private const val baseUrl: String = "https://api.npoint.io/"

        @Volatile
        private var retrofit: Retrofit? = null

        fun getRetrofit(): Retrofit {
            return retrofit ?: synchronized(this) {
                build()
            }
        }

        private fun build(): Retrofit {
            val httpClient = getHttpClientBuilder()
            addHeadersInterceptor(httpClient)
            httpClient.addInterceptor(loggingInterceptor)
            return getRetroFit(httpClient)
        }

        private fun getRetroFit(httpClient: OkHttpClient.Builder): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create().asLenient())
                .client(httpClient.build())
                .build()
        }

        private fun getHttpClientBuilder(): OkHttpClient.Builder {
            val httpClient = OkHttpClient().newBuilder()
            httpClient.readTimeout(60, TimeUnit.SECONDS)
            httpClient.connectTimeout(60, TimeUnit.SECONDS)
            return httpClient
        }

        private fun addHeadersInterceptor(httpClient: OkHttpClient.Builder) {
            val interceptor = Interceptor { chain ->
                val builder = chain.request().newBuilder()

                for (header in defaultHeaders) {
                    builder.addHeader(header.key, header.value)
                }

                val request = builder.build()
                chain.proceed(request)
            }
            httpClient.networkInterceptors().add(interceptor)
        }
    }
}
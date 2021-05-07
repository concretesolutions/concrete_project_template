package br.com.concrete.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun networkModule(
    baseUrl: String,
    enableLog: Boolean
) = module {
    single {
        val okHttpClient = OkHttpClient.Builder()
        if (enableLog) {
            okHttpClient.addInterceptor(HttpLoggingInterceptor())
        }

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient.build())
            .build()
    }

    single {
        val retrofit = get<Retrofit>()
        retrofit.create(GithubService::class.java)
    }
}
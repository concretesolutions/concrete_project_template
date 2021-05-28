package br.com.concrete.network

import br.com.concrete.network.adapter.RepositoryAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

fun networkModule(
    baseUrl: String,
    enableLog: Boolean,
) = module {
    single {
        val okHttpClient = OkHttpClient.Builder()
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BASIC

        if (enableLog) {
            okHttpClient.addInterceptor(logger)
        }

        val moshi = Moshi.Builder()
            .add(RepositoryAdapter())
            .addLast(KotlinJsonAdapterFactory())
            .build()

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient.build())
            .build()
    }

    single {
        val retrofit = get<Retrofit>()
        retrofit.create(GithubService::class.java)
    }
}
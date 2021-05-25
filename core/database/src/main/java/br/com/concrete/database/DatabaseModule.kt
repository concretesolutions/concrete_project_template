package br.com.concrete.database

import android.content.Context
import androidx.room.Room
import org.koin.dsl.module

fun databaseModule(
    context: Context
) = module {
    single {
        Room.databaseBuilder(
            context, AppDatabase::class.java, "github-database"
        ).build()
    }

    single {
        get<AppDatabase>().favoritesDao()
    }
}

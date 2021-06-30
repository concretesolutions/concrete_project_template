package br.com.concrete.base

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.map

class DataStoreManager(context: Context) {

    private val appContext = context
    private val dataStore = appContext.dataStore

    companion object {
        private val ACTUAL_THEME = intPreferencesKey("theme")
    }

    val actualTheme = dataStore.data.map { theme ->
        theme[ACTUAL_THEME]
    }

    suspend fun saveTheme(id: Int) {
        dataStore.edit { theme ->
            theme[ACTUAL_THEME] = id
        }
    }
}

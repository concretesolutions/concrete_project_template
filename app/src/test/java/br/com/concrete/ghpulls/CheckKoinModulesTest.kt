package br.com.concrete.ghpulls

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest

import org.koin.test.KoinTest
import org.koin.test.check.checkModules

@RunWith(AndroidJUnit4::class)
class CheckKoinModulesTest : AutoCloseKoinTest() {
    private lateinit var context: Context

    @Before
    fun getContext() {
       context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun checkAllKoinModules() {
        checkModules {
            modules(koinModules("https://example.com", false, context))
        }
    }
}
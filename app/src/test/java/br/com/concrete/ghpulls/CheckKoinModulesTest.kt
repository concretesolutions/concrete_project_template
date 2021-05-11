package br.com.concrete.ghpulls

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

import org.koin.test.KoinTest
import org.koin.test.check.checkModules

@RunWith(AndroidJUnit4::class)
class CheckKoinModulesTest {
    @Test
    fun checkAllKoinModules() {
        checkModules {
            modules(koinModules("https://example.com", false))
        }
    }
}
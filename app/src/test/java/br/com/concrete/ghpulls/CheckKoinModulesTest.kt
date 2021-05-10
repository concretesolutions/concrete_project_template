package br.com.concrete.ghpulls

import org.junit.Test

import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class CheckKoinModulesTest : KoinTest {
    @Test
    fun checkAllKoinModules() = checkModules {
        modules(koinModules("https://example.com", false))
    }
}
package br.com.concrete.ghpulls

import org.junit.Test

import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class CheckKoinModules : KoinTest {
    @Test
    fun checkAllKoinModules() = checkModules {
        modules(koinModules("https://example.com", false))
    }
}
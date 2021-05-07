package br.com.concrete.ghpulls

import br.com.concrete.network.networkModule
import org.koin.core.module.Module

fun koinModules(
    baseUrl: String,
    enableLog: Boolean
): List<Module> {
    val networkModule = networkModule(baseUrl, enableLog)
    return listOf(networkModule)
}
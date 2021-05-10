package br.com.concrete.ghpulls

import br.com.concrete.ghpulls.ui.repos.ReposMapper
import br.com.concrete.ghpulls.ui.repos.ReposViewModel
import br.com.concrete.network.networkModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

private val reposModule = module {
    factory { ReposMapper() }
    viewModel { ReposViewModel(get(), get()) }
}

fun koinModules(
    baseUrl: String,
    enableLog: Boolean,
): List<Module> {
    val networkModule = networkModule(baseUrl, enableLog)
    return listOf(networkModule, reposModule)
}
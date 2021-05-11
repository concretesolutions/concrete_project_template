package br.com.concrete.ghpulls

import android.content.Context
import br.com.concrete.database.databaseModule
import br.com.concrete.ghpulls.ui.repos.DBReposViewModel
import br.com.concrete.ghpulls.ui.repos.ReposMapper
import br.com.concrete.ghpulls.ui.repos.ReposViewModel
import br.com.concrete.network.networkModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

private val reposModule = module {
    factory { ReposMapper(get()) }
    viewModel { ReposViewModel(get(), get()) }
    viewModel { DBReposViewModel(get(), get()) }
}

fun koinModules(
    baseUrl: String,
    enableLog: Boolean,
    context: Context
): List<Module> {
    val networkModule = networkModule(baseUrl, enableLog)
    val databaseModule = databaseModule(context)
    return listOf(networkModule, reposModule)
}
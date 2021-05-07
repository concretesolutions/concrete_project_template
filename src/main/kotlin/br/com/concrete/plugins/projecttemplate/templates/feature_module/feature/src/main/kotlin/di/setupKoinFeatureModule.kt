package br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.kotlin.di

import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.kotlin.MainKotlinScope

fun MainKotlinScope.setupKoinFeatureModule() = createFile(
    name = "${data.featureName}Module.kt",
    rootDirectory = "${directory}/di",
    content = """
package ${data.packageName}

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ${data.featureName.toLowerCase()}Module = module {

    viewModel { ${data.activityName}ViewModel() }

}
""")
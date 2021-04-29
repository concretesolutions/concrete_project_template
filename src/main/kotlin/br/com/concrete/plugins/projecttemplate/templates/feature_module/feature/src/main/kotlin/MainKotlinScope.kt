package br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.kotlin

import br.com.concrete.plugins.projecttemplate.core.base.models.PackageScope
import br.com.concrete.plugins.projecttemplate.templates.feature_module.base.models.FeatureModuleData
import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.MainScope

class MainKotlinScope(
    val data: FeatureModuleData,
    rootDirectory: String
): PackageScope(data.project) {

    override val directory: String = "${rootDirectory}/kotlin/${data.packageName}"

}

fun MainScope.kotlinScope(block: MainKotlinScope.() -> Unit) = MainKotlinScope(this.data, this.directory).block()
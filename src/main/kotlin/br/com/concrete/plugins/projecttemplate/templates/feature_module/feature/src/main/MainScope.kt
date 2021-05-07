package br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main

import br.com.concrete.plugins.projecttemplate.core.base.models.PackageScope
import br.com.concrete.plugins.projecttemplate.templates.feature_module.base.models.FeatureModuleData
import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.RootScope

class MainScope(
    val data: FeatureModuleData,
    rootDirectory: String
): PackageScope(data.project) {

    override val directory: String = "${rootDirectory}/src/main"

}

fun RootScope.mainScope(block: MainScope.() -> Unit) = MainScope(this.data, this.directory).block()
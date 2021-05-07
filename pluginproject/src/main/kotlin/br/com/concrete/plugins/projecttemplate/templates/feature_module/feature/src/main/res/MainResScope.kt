package br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.res

import br.com.concrete.plugins.projecttemplate.core.base.models.PackageScope
import br.com.concrete.plugins.projecttemplate.templates.feature_module.base.models.FeatureModuleData
import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.MainScope

class MainResScope(
    val data: FeatureModuleData,
    rootDirectory: String
): PackageScope(data.project) {

    override val directory: String = "${rootDirectory}/res"

}

fun MainScope.resScope(block: MainResScope.() -> Unit) = MainResScope(this.data, this.directory).block()
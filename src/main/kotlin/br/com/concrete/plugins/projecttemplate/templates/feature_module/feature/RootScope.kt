package br.com.concrete.plugins.projecttemplate.templates.feature_module.feature

import br.com.concrete.plugins.projecttemplate.core.base.models.PackageScope
import br.com.concrete.plugins.projecttemplate.templates.feature_module.FeatureModuleTemplate
import br.com.concrete.plugins.projecttemplate.templates.feature_module.base.models.FeatureModuleData

class RootScope(
    val data: FeatureModuleData
): PackageScope(data.project) {

    override val directory: String = "feature/${data.featureName}"

}

fun FeatureModuleTemplate.rootScope(block: RootScope.() -> Unit) = RootScope(this.data).block()
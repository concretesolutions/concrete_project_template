package br.com.concrete.plugins.projecttemplate.templates.feature_module.base.models

import br.com.concrete.plugins.projecttemplate.core.base.enums.DependencyInjectionEnum
import com.intellij.openapi.project.Project

data class FeatureModuleData(
    val project: Project,
    val packageName: String,
    val featureName: String,
    val activityName: String,
    val dependencyInjection: DependencyInjectionEnum
)
package br.com.concrete.plugins.projecttemplate.base.models

import br.com.concrete.plugins.projecttemplate.base.enums.DependencyInjectionEnum

data class SetupData(
    val packageName: String,
    val activityTitle: String,
    val activityClass: String,
    val layoutName: String,
    val addSharedTests: Boolean,
    val dependencyInjection: DependencyInjectionEnum
)
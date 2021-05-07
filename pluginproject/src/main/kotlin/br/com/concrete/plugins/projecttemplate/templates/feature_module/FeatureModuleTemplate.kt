package br.com.concrete.plugins.projecttemplate.templates.feature_module

import br.com.concrete.plugins.projecttemplate.core.base.enums.DependencyInjectionEnum
import br.com.concrete.plugins.projecttemplate.templates.feature_module.base.models.FeatureModuleData
import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.setupBuildGradle
import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.rootScope
import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.kotlin.MainKotlinScope
import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.kotlin.activity.setupActivity
import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.kotlin.activity.setupViewModel
import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.kotlin.di.setupKoinFeatureModule
import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.kotlin.kotlinScope
import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.mainScope
import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.res.layout.setupActivityLayout
import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.res.resScope
import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.res.values.setupValuesString
import br.com.concrete.plugins.projecttemplate.templates.feature_module.feature.src.main.setupAndroidManifest

class FeatureModuleTemplate(
    val data: FeatureModuleData
){

    fun setup() = rootScope {
        setupBuildGradle()

        mainScope{
            setupAndroidManifest()

            kotlinScope {
                setupDependencyInjection(this)
                setupActivity()
                setupViewModel()
            }

            resScope {
                setupActivityLayout()
                setupValuesString()
            }
        }
    }

    private fun setupDependencyInjection(kotlinScope: MainKotlinScope) = when(data.dependencyInjection){
        DependencyInjectionEnum.Koin -> kotlinScope.setupKoinFeatureModule()

        DependencyInjectionEnum.Kodein -> Unit

        DependencyInjectionEnum.Nenhum -> Unit
    }

}
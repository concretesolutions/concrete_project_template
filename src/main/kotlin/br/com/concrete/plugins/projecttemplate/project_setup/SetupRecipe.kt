package br.com.concrete.plugins.projecttemplate.project_setup

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.addAllKotlinDependencies
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.android.tools.idea.wizard.template.impl.activities.common.navigation.navigationDependencies
import com.android.tools.idea.wizard.template.impl.activities.common.navigation.saveFragmentAndViewModel
import br.com.concrete.plugins.projecttemplate.base.enums.DependencyInjectionEnum
import br.com.concrete.plugins.projecttemplate.base.models.MainActivityData
import br.com.concrete.plugins.projecttemplate.base.models.SetupData
import br.com.concrete.plugins.projecttemplate.project_setup.res.values.dimensXml
import br.com.concrete.plugins.projecttemplate.project_setup.src.app_package.mainActivityKt
import java.io.File

fun RecipeExecutor.setupRecipe(
    moduleData: ModuleTemplateData,
    setupData: SetupData
) = with(setupData) {

    val (projectData, srcOut, resOut) = moduleData
    val appCompatVersion = 28
    val useAndroidX = moduleData.projectTemplateData.androidXSupport
    val useMaterial2 = useAndroidX || hasDependency("com.google.android.material:material")
    val generateKotlin = true
    val isLauncher = true
    addAllKotlinDependencies(moduleData)

    addDependency("com.android.support:appcompat-v7:${appCompatVersion}.+")
    addDependency("com.android.support:design:${appCompatVersion}.+")
    addDependency("com.android.support.constraint:constraint-layout:+")

    when(dependencyInjection){
        DependencyInjectionEnum.KOIN -> addDependency("io.insert-koin:koin-core:3.0.1")

        DependencyInjectionEnum.KODEIN -> addDependency("org.kodein.di:kodein-di:7.5.0")

        DependencyInjectionEnum.NONE -> Unit
    }

    generateManifest(
        moduleData = moduleData,
        activityClass = activityClass,
        activityTitle = activityTitle,
        packageName = packageName,
        isLauncher = isLauncher,
        hasNoActionBar = false,
        generateActivityTitle = true,
        requireTheme = false,
        useMaterial2 = true
    )

    val language = projectData.language
    saveFragmentAndViewModel(resOut, srcOut, language, packageName, "home", useAndroidX)
    saveFragmentAndViewModel(resOut, srcOut, language, packageName, "dashboard", useAndroidX)
    saveFragmentAndViewModel(resOut, srcOut, language, packageName, "notifications", useAndroidX)
    navigationDependencies(generateKotlin, useAndroidX, appCompatVersion)

    copy(File("ic_dashboard_black_24dp.xml"), resOut.resolve("drawable/ic_dashboard_black_24dp.xml"))

    val mainActivityData = MainActivityData(packageName, activityTitle, activityClass, layoutName)
    val mainActivity = mainActivityKt(mainActivityData, useAndroidX, useMaterial2)
    save(mainActivity, srcOut.resolve("${activityClass}.kt"))

    mergeXml(dimensXml(), resOut.resolve("values/dimens.xml"))
    open(resOut.resolve("layout/${layoutName}.xml"))
}
package br.com.concrete.plugins.projecttemplate.templates.feature_module.feature

import br.com.concrete.plugins.projecttemplate.core.base.constants.Dependencies
import br.com.concrete.plugins.projecttemplate.core.base.enums.DependencyInjectionEnum

fun RootScope.setupBuildGradle() = createFile(
    name = "build.gradle.kts",
    content = """
plugins {
    id("com.android.application")
    kotlin("android")
}
          
android {
    compileSdkVersion(30)
    defaultConfig {
        minSdkVersion(23)
        targetSdkVersion(30)
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation("${Dependencies.kotlin}")
    implementation("${Dependencies.ktx}")
    implementation("${Dependencies.constraint_layout}")
    implementation("${Dependencies.app_compat}")
    implementation("${Dependencies.material}")
    implementation("${Dependencies.coroutines}")
    implementation("${Dependencies.android_coroutines}")
    ${when(data.dependencyInjection){
        DependencyInjectionEnum.Koin -> "implementation(\"${Dependencies.koin}\")"

        DependencyInjectionEnum.Kodein -> "implementation(\"${Dependencies.kodein}\")"

        DependencyInjectionEnum.Nenhum -> ""
    }}
    
    testImplementation("${Dependencies.junit}")
    
    androidTestImplementation("${Dependencies.android_junit}")
    androidTestImplementation("${Dependencies.espresso}")
}
""")
package br.com.concrete.plugins.projecttemplate.core.base.constants

object Dependencies {

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val ktx = "androidx.core:core-ktx:${Versions.ktx}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

    //Android
    const val app_compat = "androidx.appcompat:appcompat:${Versions.app_compat}"
    const val constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.constraint_layout}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val android_coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    //Dependency Injection
    const val koin = "io.insert-koin:koin-core:${Versions.koin}"
    const val kodein = "org.kodein.di:kodein-di:${Versions.kodein}"

    //Test
    const val junit = "junit:junit:${Versions.junit}"
    const val android_junit = "androidx.test.ext:junit:${Versions.android_junit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"

}
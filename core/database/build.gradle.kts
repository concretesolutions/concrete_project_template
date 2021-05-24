plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

dependencies {
    implementation(libs.koin.core)
    implementation(libs.bundles.room)
    implementation(libs.paging)
    implementation(projects.core.model)
    kapt(libs.room.compiler)
}
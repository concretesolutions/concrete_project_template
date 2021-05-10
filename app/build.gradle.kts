plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    defaultConfig {
        applicationId = "br.com.concrete.ghpulls"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(projects.core.base)
    implementation(projects.core.network)
    implementation(projects.core.model)

    implementation(libs.material)
    implementation(libs.constraintlayout)

    implementation(libs.retrofit.core)
    implementation(libs.ktx.core)
    implementation(libs.bundles.architectureEssentials)
    implementation(libs.bundles.koinViewModel)
    implementation(libs.paging)

    implementation(libs.bundles.navigation)

    testImplementation(libs.junit)
    testImplementation(libs.koin.test)

    androidTestImplementation(libs.bundles.espressoEssentials)
}
plugins {
    id("kotlin")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.koin.core)
    implementation(libs.bundles.retrofit)

    testImplementation(libs.junit)
}
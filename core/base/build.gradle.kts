plugins {
    id("com.android.library")
    id("kotlin-android")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies{

    implementation(libs.bundles.datastore)
    implementation(libs.bundles.architectureEssentials)
    implementation(libs.coroutines)

}
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    java
    kotlin("jvm") version "1.4.32"
    id("org.jetbrains.intellij") version "0.7.2"
}

group = properties("pluginGroup")
version = properties("pluginVersion")

if (!hasProperty("StudioCompilePath")) {
    throw GradleException("No StudioCompilePath value was set, please create gradle.properties file")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

intellij {
    pluginName = properties("pluginName")
    version = properties("platformVersion")
    type = properties("platformType")
    downloadSources = properties("platformDownloadSources").toBoolean()
    updateSinceUntilBuild = true

    intellij.localPath = if (project.hasProperty("StudioRunPath")) properties("StudioRunPath") else properties("StudioRunPath")
    setPlugins(*properties("platformPlugins").split(',').map(String::trim).filter(String::isNotEmpty).toTypedArray())
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = properties("jvmVersion")
        targetCompatibility = properties("jvmVersion")
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = properties("jvmVersion")
    }

    patchPluginXml {
        version(properties("pluginVersion"))
        sinceBuild(properties("pluginSinceBuild"))
        untilBuild(properties("pluginUntilBuild"))

        changeNotes("""
            Versão inicial do plugin para criar projeto seguindo recomendações da Concrete
            """
        )
    }

    runPluginVerifier {
        ideVersions(properties("pluginVerifierIdeVersions"))
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token(System.getenv("PUBLISH_TOKEN"))
        channels(properties("pluginVersion").split('-').getOrElse(1) { "default" }.split('.').first())
    }
}

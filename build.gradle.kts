@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    alias(furnace.plugins.kotlin.jvm)
}

subprojects {
    group = "io.github.gabriel.furnace"
    version = "1.0-SNAPSHOT"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}
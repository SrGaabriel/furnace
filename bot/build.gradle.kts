@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("jvm")
    alias(furnace.plugins.kotlinx.serialization)
}

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":api"))

    implementation(furnace.deck)
    implementation(furnace.clubs)
}
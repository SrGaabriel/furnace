@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("jvm")
    alias(furnace.plugins.kotlinx.serialization)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":api"))

    implementation(furnace.deck.core)
    implementation(furnace.clubs)
}
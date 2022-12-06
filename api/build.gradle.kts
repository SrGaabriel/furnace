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
    implementation(furnace.ktor.client.core)
    implementation(furnace.ktor.client.cio)
    implementation(furnace.ktor.client.logging)
    implementation(furnace.ktor.client.serialization)
    implementation(furnace.ktor.client.content.negotiation)
    implementation(furnace.ktor.serialization.kotlinx.json)
}
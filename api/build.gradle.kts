plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.6.10"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.ktor:ktor-client-core:2.0.0")
    implementation("io.ktor:ktor-client-cio:2.0.0")
    implementation("io.ktor:ktor-client-logging:2.0.0")
    implementation("io.ktor:ktor-client-serialization:2.0.0")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.0.0")
    implementation("io.ktor:ktor-client-content-negotiation:2.0.0")
}
plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.6.10"
}

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":api"))

    implementation("org.jetbrains.exposed:exposed-core:0.38.1")
    implementation("com.github.SrGaabriel.deck:bot-deck-core:0.0.6-BOT")
    implementation("com.github.SrGaabriel.clubs:clubs-bot:0.8-SNAPSHOT")
}
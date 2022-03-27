plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
    maven("https://jitpack.io/")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":api"))

    implementation("com.github.SrGaabriel.deck:bot-deck-core:0.0.4-BOT")
    implementation("com.github.SrGaabriel.clubs:clubs-bot:0.7.1-SNAPSHOT")
}
plugins {
    kotlin("jvm") version "1.6.10"
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
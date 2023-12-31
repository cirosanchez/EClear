import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    kotlin("jvm") version "1.9.21"
    id ("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "me.cirosanchez.eclear"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")
    maven (
        url = "https://jitpack.io"
    )
}

dependencies {
    // Spigot and Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.0-Beta2")
    compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")

    // Commands
    implementation("com.github.Revxrsal.Lamp:common:3.1.8")
    implementation("com.github.Revxrsal.Lamp:bukkit:3.1.8")

    // Adventure
    implementation("net.kyori:adventure-api:4.14.0")
    implementation("net.kyori:adventure-platform-bukkit:4.3.1")
    implementation("net.kyori:adventure-text-minimessage:4.14.0")

}

kotlin {
    jvmToolchain(8)
}

tasks.withType<JavaCompile> { // Preserve parameter names in the bytecode
    options.compilerArgs.add("-parameters")
}

tasks.withType<KotlinJvmCompile> { // optional: if you're using Kotlin
    compilerOptions {
        javaParameters = true
    }
}

tasks {
    shadowJar {
        minimize()
    }
}
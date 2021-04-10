import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.31"
    application
}

group = "me.simpleton"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    maven("https://dl.bintray.com/mipt-npm/dev")
}

dependencies {
    implementation("kscience.plotlykt:plotlykt-server:0.3.0")
    testImplementation(kotlin("test-junit"))
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("MainKt")
}
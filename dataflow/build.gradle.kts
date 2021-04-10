import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.31"
    antlr
    application
}

group = "me.simpleton"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit"))
    antlr("org.antlr:antlr4:4.7.2")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.generateGrammarSource {
    arguments = listOf("-lib", "src/main/antlr")
    outputDirectory = File("src/main/java/ru/itmo/mse/simpleton/dataflow")
}

application {
    mainClass.set("MainKt")
}
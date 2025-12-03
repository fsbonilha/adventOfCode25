plugins {
    kotlin("jvm") version "1.9.21"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
}

tasks.register<JavaExec>("runDay") {
    group = "application"
    description = "Run specific AoC day"

    classpath = sourceSets.main.get().runtimeClasspath
    mainClass.set("day${project.findProperty("day") ?: "1"}.Day${project.findProperty("day") ?: "1"}Kt")
}

(1..12).forEach { day ->
    tasks.register<JavaExec>("day$day") {
        group = "aoc"
        classpath = sourceSets.main.get().runtimeClasspath
        mainClass.set("day$day.Day${day}Kt")
    }
}

tasks.register("runAll") {
    group = "aoc"
    description = "Run all AoC days"

    dependsOn((1..12).map { "day$it" })
}

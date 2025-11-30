plugins {
    kotlin("jvm") version "2.1.20"
    id("io.ktor.plugin") version "2.3.4"
    kotlin("plugin.serialization") version "1.9.0"
}

group = "org.calvin.erfmann"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // Ktor server dependencies
    implementation("io.ktor:ktor-server-netty:2.3.4")
    implementation("io.ktor:ktor-server-core:2.3.4")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")

    //Logging
    implementation("ch.qos.logback:logback-classic:1.5.13")

    //Ktor Websockets and CORS
    implementation("io.ktor:ktor-server-websockets:2.3.4")
    implementation("io.ktor:ktor-server-cors:2.3.4")
    implementation("io.ktor:ktor-client-core:2.3.4")
    implementation("io.ktor:ktor-client-cio:2.3.4")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.4")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(23)
}

tasks.register<Jar>("fatJar") {
    group = "build"
    description = "Assemble a fat jar with all dependencies."

    archiveClassifier.set("all")

    manifest {
        attributes["Main-Class"] = "MainKt"  // ggf. anpassen auf dein Main Package
    }

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
plugins {
    kotlin("jvm") version "2.1.21"
}

group = "org.jesperancinha.books"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.ktor:ktor-bom:3.1.3"))
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-gson-jvm")
    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation(platform("org.junit:junit-bom:5.12.2"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.platform:junit-platform-launcher")
    testImplementation("org.junit.platform:junit-platform-engine")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
}

tasks.withType(Test::class) {
    useJUnitPlatform()
    maxParallelForks = 4
    forkEvery = 1

    testLogging {
        events("started", "passed", "failed", "skipped")
        showStandardStreams = true
    }
}

kotlin {
    jvmToolchain(21)
}
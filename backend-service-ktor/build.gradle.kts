plugins {
    kotlin("jvm") version "2.1.10"
}

group = "org.jesperancinha.books"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm:3.1.1")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.6")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.6")
    implementation("io.ktor:ktor-serialization-gson-jvm:2.3.6")
    testImplementation("io.ktor:ktor-server-tests-jvm:2.3.13")
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
}

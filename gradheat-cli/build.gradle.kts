plugins {
    kotlin("jvm") version "2.0.10"
    application
}

group = "com.xhae"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(project(":gradheat-core"))
    implementation(project(":gradheat-html"))
    implementation("com.github.ajalt.clikt:clikt:3.5.0")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("gradheat.cli.MainKt")
}
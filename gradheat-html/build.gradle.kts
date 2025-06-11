plugins {
    kotlin("jvm") version "2.0.10"
}

group = "com.xhae"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":gradheat-core"))
    testImplementation(kotlin("test"))
}
java {
    sourceSets["main"].resources.srcDirs("src/main/resources")
}
tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
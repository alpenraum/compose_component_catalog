plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("maven-publish")
    id("signing")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

group = "com.alpenraum.composecomponentcatalog"
version = "1.0.0"

java {
    withJavadocJar()
    withSourcesJar()
}

dependencies {

    implementation("com.google.devtools.ksp:symbol-processing-api:1.6.20-1.0.5")
    implementation("com.github.Steppschuh:Java-Markdown-Generator:1.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
}

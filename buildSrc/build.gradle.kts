plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "${findProperty("java")}"
    }
}
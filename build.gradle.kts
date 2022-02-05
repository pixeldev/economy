plugins {
    java
    `maven-publish`
}

subprojects {
    apply(plugin="java-library")
    apply(plugin="maven-publish")

    tasks {
        java {
            toolchain {
                languageVersion.set(JavaLanguageVersion.of(8))
            }
        }

        compileJava {
            options.compilerArgs.add("-parameters")
        }
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                artifactId = "economy-${project.name}"
                from(components["java"])
            }
        }
    }

    repositories {
        mavenLocal()
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        mavenCentral()
    }
}

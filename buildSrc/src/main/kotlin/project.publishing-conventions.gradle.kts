plugins {
    id("project.common-conventions")
    `maven-publish`
}

val snapshotRepository: String by project
val releaseRepository: String by project

publishing {
    repositories {
        maven {
            url = if (project.version.toString().endsWith("-SNAPSHOT")) {
                uri(snapshotRepository)
            } else {
                uri(releaseRepository)
            }

            credentials {
                val userKey = "COSMO_CI_USER"
                val pwdKey = "COSMO_CI_PASSWORD"
                username = project.properties[userKey] as String?
                    ?: System.getenv(userKey)
                password = project.properties[pwdKey] as String?
                    ?: System.getenv(pwdKey)
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            artifactId = "${rootProject.name}-${project.name}"
            from(components["java"])
        }
    }
}
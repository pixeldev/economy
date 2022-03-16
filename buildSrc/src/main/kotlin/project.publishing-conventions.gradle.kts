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
                val userKey = "PUBLIC_REPO_USER"
                val pwdKey = "PUBLIC_REPO_PASSWORD"
                username = project.properties["COSMOGRP_$userKey"] as String?
                    ?: System.getenv(userKey)
                password = project.properties["COSMOGRP_$pwdKey"] as String?
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
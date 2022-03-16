plugins {
    id("project.publishing-conventions")
}

repositories {
    maven("https://repo.unnamed.team/repository/unnamed-public/")
}

dependencies {
    api(project(":api"))
    compileOnly(libs.spigot)
    compileOnly(libs.nmessage)
}
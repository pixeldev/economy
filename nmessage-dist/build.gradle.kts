plugins {
    id("project.publishing-conventions")
}

repositories {
    maven("https://repo.unnamed.team/repository/unnamed-public/")
}

dependencies {
    api(project(":api"))
    compileOnly("org.spigotmc:spigot-api:1.16.5-R0.1-SNAPSHOT")
    compileOnly("me.yushust.message:core:6.0.17")
}
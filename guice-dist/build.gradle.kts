plugins {
    id("project.publishing-conventions")
}

dependencies {
    api(project(":api"))
    compileOnly(libs.inject.guice)
}
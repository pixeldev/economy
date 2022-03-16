plugins {
    id("project.publishing-conventions")
}

dependencies {
    api(project(":api"))
    compileOnly("com.google.inject:guice:4.2.3")
}
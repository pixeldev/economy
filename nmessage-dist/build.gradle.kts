repositories {
    maven("https://repo.unnamed.team/repository/unnamed-public/")
}

dependencies {
    api(project(":api"))
    compileOnly("me.yushust.message:core:6.0.17")
}
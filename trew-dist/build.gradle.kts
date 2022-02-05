repositories {
    maven("https://repo.unnamed.team/repository/unnamed-public/")
}

dependencies {
    api(project(":api"))
    api("me.yushust.inject:core:0.4.5-SNAPSHOT")
}
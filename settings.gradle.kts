pluginManagement {
    repositories {
        google()
        mavenCentral() // This is where KSP is hosted
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GLB Studio"
include(":app")
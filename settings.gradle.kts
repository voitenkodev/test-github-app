pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(
        RepositoriesMode.FAIL_ON_PROJECT_REPOS
    )

    repositories {
        google()
        mavenCentral()
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "TestGithubApp"

include(
    ":app",

    ":data:remote",

    ":domain:repositories",

    ":common:design-system",
    ":common:architecture-component",

    ":features:github-repositories",
    ":features:repository-details",
)
 
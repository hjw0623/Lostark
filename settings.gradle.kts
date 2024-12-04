pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
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

rootProject.name = "Lostark"
include(":app")
include(":character:data")
include(":character:domain")
include(":character:presentation")
include(":core:presnetation:designsystem")
include(":core:presnetation:ui")
include(":core:domain")
include(":core:data")
include(":core:database")
include(":events:data")
include(":events:presentation")
include(":events:domain")

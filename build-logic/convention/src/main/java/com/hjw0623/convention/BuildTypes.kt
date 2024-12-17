package com.hjw0623.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.BuildType
import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

internal fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *>,
    extensionType: ExtensionType
) {
    commonExtension.run {
        buildFeatures {
            buildConfig = true
        }

        val apikey = gradleLocalProperties(rootDir).getProperty("API_KEY")
        when (extensionType) {
            ExtensionType.APPLICATION -> {
                extensions.configure<ApplicationExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(apikey)
                        }
                        release {
                            configureReleaseBuildType(commonExtension, apikey)
                        }
                    }
                }
            }

            ExtensionType.LIBRARY -> {
                extensions.configure<LibraryExtension> {
                    buildTypes {
                        debug {
                            configureDebugBuildType(apikey)
                        }
                        release {
                            configureReleaseBuildType(commonExtension, apikey)
                        }
                    }
                }
            }
        }
    }
}

private fun BuildType.configureDebugBuildType(apikey: String) {
    buildConfigField("String", "API_KEY", "\"$apikey\"")
    buildConfigField("String", "BASE_URL", "\"https://developer-lostark.game.onstove.com\"")
}
private fun BuildType.configureReleaseBuildType(
    commonExtension: CommonExtension<*, *, *, *, *>,
    apikey: String
) {
    buildConfigField("String", "API_KEY", "\"$apikey\"")
    buildConfigField("String", "BASE_URL", "\"https://developer-lostark.game.onstove.com\"")

    isMinifyEnabled = true
    proguardFiles(
        commonExtension.getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
    )
}
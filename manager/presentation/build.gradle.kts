plugins {
    alias(libs.plugins.lostark.android.feature.ui)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.hjw0623.manager.presentation"
}

dependencies {
    implementation(projects.character.domain)
    implementation(projects.core.domain)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.gson)
    implementation(libs.coil.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.timber)
}
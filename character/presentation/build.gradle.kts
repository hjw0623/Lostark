plugins {
    alias(libs.plugins.lostark.android.feature.ui)
}

android {
    namespace = "com.hjw0623.character.presentation"
}

dependencies {
    implementation(projects.character.domain)
    implementation(projects.core.domain)

    implementation(libs.coil.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.timber)
}
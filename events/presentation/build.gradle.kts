plugins {
    alias(libs.plugins.lostark.android.feature.ui)
}

android {
    namespace = "com.hjw0623.events.presentation"
}

dependencies {
    implementation(projects.events.domain)
    implementation(projects.core.domain)


    implementation(libs.coil.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.timber)
}
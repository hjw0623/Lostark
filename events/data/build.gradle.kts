plugins {
    alias(libs.plugins.lostark.android.library)
}

android {
    namespace = "com.hjw0623.events.data"
}

dependencies {
    implementation(projects.events.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.work)
    implementation(libs.koin.android.workmanager)
    implementation(libs.kotlinx.serialization.json)
}
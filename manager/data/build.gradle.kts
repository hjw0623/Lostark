plugins {
    alias(libs.plugins.lostark.android.library)
    alias(libs.plugins.lostark.jvm.ktor)
    alias(libs.plugins.lostark.android.room)
}

android {
    namespace = "com.hjw0623.manager.data"
}

dependencies {
    implementation(libs.bundles.koin)
    implementation(projects.manager.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}
plugins {
    alias(libs.plugins.lostark.android.library)
    alias(libs.plugins.lostark.jvm.ktor)
}

android {
    namespace = "com.hjw0623.manager.data"
}

dependencies {
    implementation(libs.bundles.koin)
    implementation(projects.character.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}
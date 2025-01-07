plugins {
    alias(libs.plugins.lostark.android.library)
    alias(libs.plugins.lostark.jvm.ktor)
}

android {
    namespace = "com.hjw0623.core.data"
}

dependencies {
    implementation(libs.timber)

    implementation(libs.bundles.koin)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}
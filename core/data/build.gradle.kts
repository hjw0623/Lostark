plugins {
    alias(libs.plugins.lostark.android.library)
}

android {
    namespace = "com.hjw0623.core.data"
}

dependencies {
    implementation(libs.timber)

    implementation(projects.core.domain)
    implementation(projects.core.database)
}
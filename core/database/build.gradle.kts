plugins {
    alias(libs.plugins.lostark.android.library)
}

android {
    namespace = "com.hjw0623.core.database"
}

dependencies {
    implementation(projects.core.domain)
}
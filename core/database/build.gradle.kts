plugins {
    alias(libs.plugins.lostark.android.library)
    alias(libs.plugins.lostark.android.room)
}

android {
    namespace = "com.hjw0623.core.database"
}

dependencies {
    implementation(projects.core.domain)
}
plugins {
    alias(libs.plugins.lostark.android.library)
    alias(libs.plugins.lostark.jvm.ktor)
}

android {
    namespace = "com.hjw0623.events.data"
}

dependencies {
    implementation(projects.events.domain)
    implementation(projects.core.domain)
    implementation(projects.core.data)
}
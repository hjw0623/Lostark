plugins {
    alias(libs.plugins.lostark.jvm.library)
}

dependencies {
    implementation(projects.core.domain)
}
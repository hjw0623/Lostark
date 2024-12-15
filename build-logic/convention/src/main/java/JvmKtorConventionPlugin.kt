import com.android.build.gradle.LibraryExtension
import com.hjw0623.convention.ExtensionType
import com.hjw0623.convention.configureBuildTypes
import com.hjw0623.convention.configureKotlinAndroid
import com.hjw0623.convention.configureKotlinJvm
import com.hjw0623.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class JvmKtorConventionPlugin: Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

            dependencies {
                "implementation"(libs.findBundle("ktor").get())
            }
        }
    }
}
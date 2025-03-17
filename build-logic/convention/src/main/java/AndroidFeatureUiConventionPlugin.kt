import com.codermp.build_logic.convention.addUiLayerDependencies
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Gradle convention plugin that applies the configuration for a UI Feature module.
 */
class AndroidFeatureUiConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.apply {
                apply("runfit.android.library.compose")
            }

            dependencies {
                "implementation"(project(":core:presentation:ui"))
                "implementation"(project(":core:presentation:designsystem"))

                addUiLayerDependencies(target)
            }
        }
    }
}
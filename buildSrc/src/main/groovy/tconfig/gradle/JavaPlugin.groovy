package tconfig.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.plugins.ide.eclipse.EclipsePlugin
import org.gradle.plugins.ide.idea.IdeaPlugin

public class JavaPlugin implements Plugin<Project> {


    @Override
    public void apply(Project project) {
        project.apply({ it.plugin(org.gradle.api.plugins.JavaPlugin.class) })
        createProvidedConfiguration(project)
    }

    void createProvidedConfiguration(Project p) {
        p.configurations {
            provided
        }

        p.sourceSets.main.compileClasspath += [p.configurations.provided]
        p.sourceSets.test.compileClasspath += [p.configurations.provided]
        p.sourceSets.test.runtimeClasspath += [p.configurations.provided]

        p.apply({ it.plugin(IdeaPlugin.class) });
        p.apply({ it.plugin(EclipsePlugin.class) });
        p.idea.module {
            scopes.PROVIDED.plus += [p.configurations.provided]
        }
        p.eclipse.classpath { plusConfigurations += [p.configurations.provided] }
    }
}

package tconfig.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

public class JavaPlugin implements Plugin<Project> {


    @Override
    public void apply(Project project) {
        project.apply({ it.plugin(org.gradle.api.plugins.JavaPlugin.class) })
    }

}

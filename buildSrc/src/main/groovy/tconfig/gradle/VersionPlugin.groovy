package tconfig.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project;

public class VersionPlugin implements Plugin<Project> {


    @Override
    void apply(Project project) {
        if (!(project.version instanceof Version)) {
            project.version = new Version(project.version as String);
        }
        def buildNumber = project.getProperty('BUILD_NUMBER')
        if (buildNumber != null) {
            project.version.build = buildNumber as Integer;
        }
    }
}

package cucumber.contrib.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.JavaExec


class CucumberPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.configurations {
            cucumberRuntime.extendsFrom(testRuntime)
        }

        def cucumber = project.task('cucumber', type: JavaExec)

        cucumber.configure {
            main 'cucumber.api.cli.Main'
            debug project.hasProperty('cukeDebug') && Boolean.parseBoolean(project.cukeDebug)

            enableAssertions true
            classpath = project.configurations.cucumberRuntime + project.sourceSets.main.output + project.sourceSets.test.output

            def features = project.sourceSets.test.resources.srcDirs

            args '-f', 'pretty',
                 '-f', 'html:' + project.file("$project.buildDir/cucumber-html-reports"),
                 '-f', 'junit:' + project.file("$project.buildDir/cucumber.xml"),
                 '-f', 'json:' + project.file("$project.buildDir/cucumber.json")

            args += features*.absolutePath

            if (project.hasProperty('cukeNames')) {
                args '--name', project.cukeNames
            }

            if (project.hasProperty('cukeTags') && !project.hasProperty('cukeNames')) {
                args '--tags', project.cukeTags
            }
        }

        cucumber.doFirst {
            def topLevelPackages = project.sourceSets.test.output.classesDir.listFiles()
            topLevelPackages.each { pkg -> cucumber.args('--glue', pkg.name) }
        }
    }
}

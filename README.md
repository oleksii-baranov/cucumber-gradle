Cucumber Gradle Plugin
===

Add the following to your build.gradle:

    buildscript {
        repositories {
            maven {
                url 'http://dl.bintray.com/ramonza/gradle-plugins'
            }
        }

        dependencies {
            classpath 'cucumber.contrib.gradle:cucumber-gradle:0.1.0'
        }
    }

    apply plugin: 'cucumber'


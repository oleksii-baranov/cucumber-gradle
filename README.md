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

This will add a `cucumber` task that runs all features in `src/test/resources` with glue defined in `src/test/java`.

The `cucumber` task extends `JavaExec` so any properties that work on `JavaExec` should also work on `cucumber`, e.g.:

    cucumber {
        systemProperty 'mySystemPropery', 'somveValue'
    }

Assertions are enabled by default. Do not modify the `mainClass` or `args` properties.

Additionally, the `cucumber` task reads certain project properties, making it easier to invoke from the commandline.

To debug the cucumber process:

    $ gradle cucumber -PcukeDebug=true

To run certain tags:

    $ gradle cucumber -PcukeTags=~@wip

To run certain features or scenarios matched by description:

    $ gradle cucumber -PcukeNames='my special scenario title'


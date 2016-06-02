package uk.gov.digital.ho.proving

import com.wiredforcode.gradle.spawn.KillProcessTask
import com.wiredforcode.gradle.spawn.SpawnPlugin
import com.wiredforcode.gradle.spawn.SpawnProcessTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.springframework.boot.gradle.SpringBootPlugin

/**
 * @Author Home Office Digital
 */
class SpringBootGradle implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.extensions.create("springboot", SpringBootPluginExtension)

        project.with {

            plugins.apply(SpringBootPlugin)
            plugins.apply(SpawnPlugin)

            configurations {
                dev
            }

            dependencies {
                compile libraries.springboot
                dev libraries.springbootDev
            }

            bootRun {
                classpath = sourceSets.main.runtimeClasspath + configurations.dev

                systemProperties = [
                        'endpoints.shutdown.enabled': true
                ]
            }

            task('bootStop', type: BootStopTask)

            task('startServer', type: SpawnProcessTask, dependsOn: 'assemble') {
                description 'Starts the server in a separate process. Stop the server using the stopServer task.'
                group 'application'
                ready 'Started ServiceRunner'

                project.afterEvaluate {
                    command "java -jar ${jar.archivePath} -Xmx1024"
                }
            }

            task('stopServer', type: KillProcessTask) {
                group 'application'
                description 'Stops the server that was started with the startServer task'
            }
        }
    }

}

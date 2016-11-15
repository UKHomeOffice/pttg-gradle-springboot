package uk.gov.digital.ho.proving.springbootgradleplugin

import com.wiredforcode.gradle.spawn.KillProcessTask
import com.wiredforcode.gradle.spawn.SpawnPlugin
import com.wiredforcode.gradle.spawn.SpawnProcessTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.springframework.boot.gradle.plugin.SpringBootPlugin

/**
 * @Author Home Office Digital
 */
class SpringBootGradle implements Plugin<Project> {

    def springBootVersion = '1.4.1.RELEASE'

    @Override
    void apply(Project project) {

        project.extensions.create("springboot", SpringBootPluginExtension)

        project.with {

            plugins.apply(SpringBootPlugin)
            plugins.apply(SpawnPlugin)

            dependencies {
                compile "org.springframework.boot:spring-boot:$springBootVersion"
                compile "org.springframework.boot:spring-boot-starter-web:$springBootVersion"
                compile "org.springframework.boot:spring-boot-starter-actuator:$springBootVersion"
            }

            task("pttgSpringBootGradlePluginUsage", type: UsageTask)

            bootRun {
                // todo tune actuator config to disable healthchecks etc in prod, change healthcheck ports, etc
                systemProperties = [
                    'endpoints.shutdown.enabled': true,
                    'endpoints.health.sensitive': false
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

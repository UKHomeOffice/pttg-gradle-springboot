package uk.gov.digital.ho.proving

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @Author Home Office Digital
 */
class BootStopTask extends DefaultTask {

    String description = "Stop the app that was started by bootRun"
    String group = "application"

    @TaskAction
    def stop() {

        def command = "curl -X POST localhost:${project.springboot.port}/shutdown"

        println("Stopping with command: '$command'")

        def proc = command.execute()
        proc.waitFor()
    }
}

package uk.gov.digital.ho.proving.springbootgradleplugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * @Author Home Office Digital
 */
class UsageTask extends DefaultTask {

    String description = "Describe how to use the plugin"
    String group = "help"

    @TaskAction
    def usageReport() {
        println "\n" +
            "************************************ USAGE ********************************************\n" +
            "pttgSpringBootGradle plugin usage guide\n" +
            "\n" +
             "This plugin applies the following configuration\n" +
            "\n" +
            "Plugins:\n" +
            "    SpringBoot\n" +
            "    Spawn\n" +
            "\n" +
            "Dependencies:\n" +
            "    spring-boot\n" +
            "    spring-boot-starter-web\n" +
            "    spring-boot-starter-actuator\n" +
            "\n" +
            "The bootRun task is modified to enable:\n" +
            "   1) detailed responses from the healthcheck endpoint\n" +
            "   2) the shutdown endpoint\n" +
            "\n" +
            "A bootStop task is added\n" +
            "    This depends on knowing the port where your spring boot app will run\n" +
            "    The default is 8081\n" +
            "    You can specify a different value with the following\n" +
            "\n" +
            "        springboot{\n" +
            "            port = 8081 // the port where your spring boot app is running. Required by the bootStop task\n" +
            "        }\n" +
            "\n" +
            "See full details in the plugin readme in github at https://github.com/UKHomeOffice/pttg-gradle-springboot"

    }
}

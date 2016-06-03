## Gradle plugin that adds Spring Boot and supporting utilitiy tasks to your build.

### Use this plugin in your gradle build by:

1. Adding a buildscript dependency

```
buildscript {
       repositories{
            maven { url "https://github.com/UKHomeOffice/pttg-gradle-repo/raw/master/releases" }
       }
       dependencies {
           classpath 'pttg-gradle-common:pttgSpringBootGradle:1.0.RELEASE'
       }
}
```

2. Applying the plugin

```
apply plugin: 'pttgSpringBootGradle'
```

3. Configuring the plugin

Customise configuration using the 'springboot' extension block and override any properties.
The available properties and their defaults are shown in this sample:

```
springboot{
    port = 8081 // the port where your spring boot app is running. Required by the bootStop task
}
```

### What this plugin gives your build automatically

1. Applies the spring boot plugin

2. Adds the spring boot dependencies

3. Adds a bootStop task alongside the standard bootRun task (defaults to use port 8081)

4. Adds the spring boot actuator support at development time when running with the bootRun task
see [spring-boot-actuator endpoints](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#production-ready-endpoints)

5. Adds startSever and stopServer tasks which use a separate process so that other gradle tasks such as acceptanceTest
can start and stop the server automatically 


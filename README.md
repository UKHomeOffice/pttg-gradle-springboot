## Gradle plugin that adds Spring Boot and supporting utilitiy tasks to your build.

### Use this plugin in your gradle build by:

1. Adding a buildscript dependency

```
buildscript {
       repositories{
            maven { url "https://github.com/UKHomeOffice/pttg-gradle-repo/raw/master/releases" }
       }
       dependencies {
           classpath 'pttg-gradle-common:pttgSpringBootGradle:1.1.RELEASE'
       }
}
```

2. Applying the plugin

```
apply plugin: 'pttgSpringBootGradle'
```

3. User guide
See the following documentation. In your project you can also execute the 'pttgSpringBootGradlePluginUsage' task to
see usage instructions and version information etc.

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

4. Adds the spring boot actuator support running with the bootRun task
see [spring-boot-actuator endpoints](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#production-ready-endpoints)

5. Adds startSever and stopServer tasks which use a separate process so that other gradle tasks such as acceptanceTest
can start and stop the server automatically 

### Springboot Actuator support
todo tune config for production

Actuator support exposes (amongst other things) the following useful endpoints

  * /mappings
  * /health
  * /metrics
  * /info
  * /trace 

Try them and see.

### Development

1. Increment the minor version number (still needs to be RELEASE because I can't figure out how to make the git-repo plugin support snapshots)
2. Make your changes
3. You can use publishMavenJavaPublicationToMavenLocal to deploy the change to your local maven repo
   1. Note however that this won't work if your changes add new transitive third-party dependencies
4. Test consumption of the plugin from another project by
   1. Adding mavenLocal() to your buildscript repositories to consume form local repo
   2. using the new version number for the plugin in your buildscript dependencies
   3. You may well need to execute ``` ./gradlew --refresh-dependencies``` to pick up changes, then refresh your IDE

plugins {
    id("com.github.node-gradle.node") version "3.5.1"
}

node {
    version.set("18.17.0") // Node.js version
    npmVersion.set("9.6.0") // NPM version
    download.set(true)      // Automatically download Node.js
    workDir.set(file("${project.buildDir}/nodejs"))  // Node.js work directory
    npmWorkDir.set(file("${project.buildDir}/npm"))  // NPM cache directory
}

tasks.register<NpmTask>("installDependencies") {
    args.set(listOf("install"))
    workingDir.set(file("src"))  // Assuming Next.js app is in the src folder
}

tasks.register<NpmTask>("buildApp") {
    args.set(listOf("run", "build"))
    dependsOn("installDependencies")
    workingDir.set(file("src"))
}

tasks.register<NpmTask>("startApp") {
    args.set(listOf("run", "start"))
    dependsOn("buildApp")
    workingDir.set(file("src"))
}

tasks.register<NpmTask>("deployApp") {
    args.set(listOf("run", "deploy"))  // Define deploy script in package.json
    dependsOn("buildApp")
    workingDir.set(file("src"))
}

tasks.named("build") {
    dependsOn("buildApp")
}

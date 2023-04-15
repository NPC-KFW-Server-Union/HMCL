plugins {
    id("java")
}

group = "org.jackhuang"
version = "3.0"

repositories {
    mavenCentral()
    maven(url = "https://repo.eclipse.org/content/groups/releases/")

}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    implementation("org.eclipse.jgit:org.eclipse.jgit:6.5.0.202303070854-r")
    implementation("org.eclipse.jgit:org.eclipse.jgit.archive:6.5.0.202303070854-r")
    implementation("org.eclipse.jgit:org.eclipse.jgit.ssh.jsch:6.5.0.202303070854-r")
    implementation("commons-io:commons-io:2.11.0")
    implementation("org.tukaani:xz:1.9")
    implementation(project(":HMCLCore"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
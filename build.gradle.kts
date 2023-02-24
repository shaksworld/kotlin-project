import groovy.xml.dom.DOMCategory.attributes
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"

}

group = "com.shaka"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	testImplementation("oi.mockk.mockk:1.10.4")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

//tasks.jar{
//	manifest.attributes["Main-Class"] = "com.shaka.newproject.NewProjectApplicationKt"
//}

tasks.withType<Jar> {
	// Otherwise you'll get a "No main manifest attribute" error
	manifest {
		attributes["Main-Class"] =  "com.shaka.newproject.NewProjectApplicationKt"
	}

	// To avoid the duplicate handling strategy error
	duplicatesStrategy = DuplicatesStrategy.EXCLUDE

	// To add all of the dependencies
	from(sourceSets.main.get().output)

	dependsOn(configurations.runtimeClasspath)
	from({
		configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
	})
}
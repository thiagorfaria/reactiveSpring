import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.1.5.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	kotlin("jvm") version "1.3.20"
	kotlin("plugin.spring") version "1.3.20"
}

group = "br.com.trf"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_12

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(module = "junit")
	}

	testImplementation("io.projectreactor:reactor-test")
	testImplementation("io.kotlintest:kotlintest-runner-junit5:3.3.2")
	testImplementation("io.kotlintest:kotlintest-extensions-spring:3.3.2")
	testImplementation("org.junit.jupiter:junit-jupiter:5.4.2")
}

val test by tasks.getting(Test::class) {
	useJUnitPlatform { }
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "12"
	}
}

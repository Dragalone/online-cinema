plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	implementation("org.mapstruct:mapstruct:1.5.3.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.3.Final")

	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")

	testImplementation("org.testcontainers:postgresql")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.postgresql:postgresql")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

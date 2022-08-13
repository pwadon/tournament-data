import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val jacksonDataType = "2.13.3"
val mockitoKotlin = "4.0.0"
val testContainerVersion = "1.17.3"

plugins {
    val springBootVersion = "2.7.2"
    val springDependencyManagementVersion = "1.0.12.RELEASE"
    val kotlinPluginVersion = "1.6.21"

    idea
    id("org.springframework.boot") version springBootVersion
    id("io.spring.dependency-management") version springDependencyManagementVersion
    kotlin("jvm") version kotlinPluginVersion
    kotlin("plugin.spring") version kotlinPluginVersion
    kotlin("plugin.jpa") version kotlinPluginVersion
}

group = "com.tournament.data"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonDataType")
    implementation("org.json:json:20220320")
    runtimeOnly("org.postgresql:postgresql")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlin")
    testImplementation("org.testcontainers:postgresql:$testContainerVersion")
    testImplementation("org.testcontainers:testcontainers:$testContainerVersion")
    testImplementation("org.testcontainers:junit-jupiter:$testContainerVersion")
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

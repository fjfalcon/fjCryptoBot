import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.fjfalcon"
version = "0.1"

plugins {
    val kotlinVersion = "1.3.72"
    val ktLintVersion = "9.2.1"

    id("org.springframework.boot") version "2.2.6.RELEASE"
    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jlleitschuh.gradle.ktlint") version ktLintVersion
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    val kotlinVersion = "1.3.72"
    val springBootVersion = "2.2.6.RELEASE"
    val telegramBotsVersion = "4.8.1"
    val jacksonModuleKotlinVersion = "2.10.3"
    val junitVersion = "5.6.2"
    val mockitoVersion = "3.3.3"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter:$springBootVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.telegram:telegrambots:$telegramBotsVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonModuleKotlinVersion")
}

repositories {
    mavenCentral()
}

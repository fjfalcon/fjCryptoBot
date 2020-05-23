import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.fjfalcon"
version = "0.1"

plugins {
    val kotlinVersion = "1.3.72"
    val ktLintVersion = "9.2.1"
    val shadowVersion = "5.2.0"

    kotlin("jvm") version kotlinVersion
    kotlin("kapt") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
    id("org.jlleitschuh.gradle.ktlint") version ktLintVersion
    id("com.github.johnrengelman.shadow") version shadowVersion
    id("application")
}

application {
    mainClassName = "com.fjfalcon.cryptobot.CryptoBotApplication"
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

tasks {
    "shadowJar"(ShadowJar::class) {
        mergeServiceFiles()
    }
}

dependencies {
    val kotlinVersion = "1.3.72"
    val telegramBotsVersion = "4.8.1"
    val jacksonModuleKotlinVersion = "2.10.3"
    val junitVersion = "5.6.2"
    val mockitoVersion = "3.3.3"
    val micronautVersion = "2.0.0.M3"

    kapt("io.micronaut:micronaut-inject-java:$micronautVersion")
    kapt("io.micronaut:micronaut-validation:$micronautVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    implementation("org.telegram:telegrambots:$telegramBotsVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonModuleKotlinVersion")
    kapt(enforcedPlatform("io.micronaut:micronaut-bom:$micronautVersion"))
    implementation(enforcedPlatform("io.micronaut:micronaut-bom:$micronautVersion"))
    implementation("io.micronaut:micronaut-inject")
    implementation("io.micronaut:micronaut-validation")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut:micronaut-http-client")
    runtimeOnly("ch.qos.logback:logback-classic:1.2.3")
}

repositories {
    mavenCentral()
    jcenter()
}

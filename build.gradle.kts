plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.0.8"
    id("com.github.johnrengelman.shadow") version "5.2.0"
    kotlin("jvm") version "1.3.71"
}

group = "alexandru.balan.simulare2"
version = "2020.1"

application {
    mainClassName = "simulare2.playground.MainKt"
}

javafx {
    version = "11"
    modules("javafx.controls", "javafx.fxml", "javafx.swing")
}

repositories {
    mavenCentral()
    jcenter()
    maven ("https://kotlin.bintray.com/kotlin-numpy")
    maven ("https://jetbrains.bintray.com/lets-plot-maven")
}

dependencies {

    implementation(kotlin("stdlib"))
    implementation ("org.jetbrains.lets-plot:lets-plot-common:1.3.1")
    implementation ("org.jetbrains.lets-plot:lets-plot-jfx:1.3.1")
    implementation ("org.jetbrains.lets-plot:lets-plot-kotlin-api:0.0.9-SNAPSHOT")
    implementation("org.jetbrains.lets-plot:kotlin-frontend-api:0.0.8-SNAPSHOT")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}
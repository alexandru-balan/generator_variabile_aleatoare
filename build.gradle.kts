plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.0.8"
    kotlin("jvm") version "1.3.71"
}

group = "alexandru.balan.simulare2"
version = "2020.alpha.1"

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
    implementation("org.jetbrains:kotlin-numpy:0.1.4")
    implementation("org.jetbrains.kotlinx", "kotlinx-coroutines-core", "1.3.5")
    testImplementation("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
}
/**
 * Copyright (C) 2016 Dr. David H. Akehurst (http://dr.david.h.akehurst.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
/*
buildscript {
    repositories {
        mavenLocal()
        google()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
    }
}
*/
plugins {
    kotlin("multiplatform") version ("1.4.10") apply false

//    id("com.android.application")  apply false
//    id("kotlin-android-extensions")  apply false
}

allprojects {

    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
    }

    val version_project: String by project
    val group_project = "${rootProject.name}"

    group = group_project
    version = version_project

    buildDir = File(rootProject.projectDir, ".gradle-build/${project.name}")

}

fun getProjectProperty(s: String) = project.findProperty(s) as String?


subprojects {
    if (project.name.startsWith("application-").not() || project.name=="application-common") {
        apply(plugin = "org.jetbrains.kotlin.multiplatform")

        configure<KotlinMultiplatformExtension> {
            jvm("jvm8") {
                val main by compilations.getting {
                    kotlinOptions {
                        jvmTarget = JavaVersion.VERSION_1_8.toString()
                        freeCompilerArgs += "-Xuse-experimental=com.soywiz.korge3d.Korge3DExperimental"
                    }
                }
                val test by compilations.getting {
                    kotlinOptions {
                        jvmTarget = JavaVersion.VERSION_1_8.toString()
                    }
                }
            }
            js(org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType.IR) {
                nodejs()
                browser()
            }
            macosX64()
            //mingwX64()
            //androidNativeArm64()
            //android()
            ios()
            sourceSets {
                val commonMain by getting {
                    kotlin.srcDir("$buildDir/generated/kotlin")
                }
            }
        }

        val now = Instant.now()
        fun fBbuildStamp(): String {
            return DateTimeFormatter.ISO_DATE_TIME.withZone(ZoneId.of("UTC")).format(now)
        }

        fun fBuildDate(): String {
            return DateTimeFormatter.ofPattern("yyyy-MMM-dd").withZone(ZoneId.of("UTC")).format(now)
        }

        fun fBuildTime(): String {
            return DateTimeFormatter.ofPattern("HH:mm:ss z").withZone(ZoneId.of("UTC")).format(now)
        }
        tasks.register<Copy>("generateFromTemplates") {
            val templateContext = mapOf(
                    "version" to project.version,
                    "buildStamp" to fBbuildStamp(),
                    "buildDate" to fBuildDate(),
                    "buildTime" to fBuildTime()
            )
            inputs.properties(templateContext) // for gradle up-to-date check
            from("src/template/kotlin")
            into("$buildDir/generated/kotlin")
            expand(templateContext)
        }
        tasks.getByName("compileKotlinMetadata") {
            dependsOn("generateFromTemplates")
        }
        tasks.getByName("compileKotlinJvm8") {
            dependsOn("generateFromTemplates")
        }
        tasks.getByName("compileKotlinJs") {
            dependsOn("generateFromTemplates")
        }

        dependencies {
            "commonTestImplementation"(kotlin("test-common"))
            "commonTestImplementation"(kotlin("test-annotations-common"))

            "jvm8TestImplementation"(kotlin("test-junit"))

            "jsTestImplementation"(kotlin("test-js"))
        }

    }
}
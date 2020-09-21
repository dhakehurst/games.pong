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

plugins {
    kotlin("multiplatform")
}

kotlin {
    js(org.jetbrains.kotlin.gradle.plugin.KotlinJsCompilerType.IR) {
        browser() {
            webpackTask {}
        }
        binaries.executable()
    }

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
}


dependencies {

    "jsMainImplementation"(project(":application-common"))

}

tasks.getByName("jvm8ProcessResources").dependsOn("jsBrowserProductionWebpack")

kotlin {
    sourceSets {
        val jvm8Main by getting {
            resources.srcDir("$buildDir/distributions")
        }
    }
}
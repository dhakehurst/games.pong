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

import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType

plugins {
    kotlin("multiplatform")
    application
}

// so that the application plugin can find the jars from the kotlin-plugin jvm configuration
val runtimeClasspath by configurations.getting {
    attributes.attribute(KotlinPlatformType.attribute, KotlinPlatformType.jvm)
}
application {
    mainClassName = "net.akehurst.games.pong.application.web.server.MainKt"
}

kotlin {
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
val version_ktor:String by project
dependencies {
    // need this so that the gradle application-plugin can find the module built by the kotlin-plugin
    runtime( project(path=":application-web-server", configuration="jvm8RuntimeElements") )

    "jvm8MainImplementation"(project(":application-web-client"))

    "jvm8MainImplementation"("io.ktor:ktor-server-core:$version_ktor")
    "jvm8MainImplementation"("io.ktor:ktor-server-jetty:$version_ktor")

    // for logging
    "jvm8MainImplementation"("org.slf4j:slf4j-simple:+")
}
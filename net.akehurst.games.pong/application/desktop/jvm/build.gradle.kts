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
    id("application")
    kotlin("multiplatform")
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

dependencies {
    "jvm8MainImplementation"(project(":application-common"))
    "jvm8MainImplementation"(project(":engineering-gui"))

    // application plugin expects stuff on the 'implementation' classpath
    implementation(project(path=":application-desktop-jvm", configuration="jvm8RuntimeElements"))
}

application {
    mainClass.set("net.akehurst.games.pong.application.desktop.jvm.MainKt")
}

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
/*
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
                useModule("com.android.tools.build:gradle:4.0.1")
            }
        }
    }
}
*/
rootProject.name = file(".").name

fileTree(".") {
    include("**/build.gradle.kts")
    exclude("build.gradle.kts") // Exclude the root build file.
}.forEach {
    val prj = moduleName(it.parentFile) //it.parentFile.name
    println("including $prj at " + relativePath(it.parent))
    include(prj)
    project(":$prj").projectDir = File(relativePath(it.parent))
}

fun moduleName(f: File): String {
    return if (f.parentFile == rootDir) {
        f.name
    } else {
        val p = moduleName(f.parentFile)
        "${p}-${f.name}"
    }
}


enableFeaturePreview("GRADLE_METADATA")
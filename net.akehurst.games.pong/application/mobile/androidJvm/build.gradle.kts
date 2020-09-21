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
plugins {
    id("com.android.application")
    kotlin("multiplatform")
    id("kotlin-android-extensions")
}

repositories {
    mavenCentral()
    google()
    jcenter()
}



kotlin {
    android()
    sourceSets {
        val androidMain by getting {
            dependencies {
                implementation(project(":application-common"))
                implementation("com.google.android.material:material:1.2.0")
                implementation("androidx.appcompat:appcompat:1.2.0")
                implementation("androidx.constraintlayout:constraintlayout:1.1.3")
            }
        }
    }
}

android {
    sourceSets {
        getByName("main") {
            manifest.srcFile("src/androidMain/resources/AndroidManifest.xml")
            java.srcDirs("src/androidMain/kotlin")
        }
    }
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.jetbrains.androidApp"
        minSdkVersion(24)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    packagingOptions {
        exclude( "META-INF/AL2.0")
        exclude( "META-INF/LGPL2.1")
    }
}
*/
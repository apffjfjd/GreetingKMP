
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    // JVM 타겟 설정 추가
    jvm {
        compilations.all {
            kotlinOptions {
                // JVM 타겟의 Kotlin 코드를 컴파일하기 위한 JVM 버전을 지정
                jvmTarget = "11" // 필요에 따라 "11" 등으로 변경 가능
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                // 공통 의존성 설정
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(kotlin("stdlib-jdk8"))
                // JVM 플랫폼 특정 의존성 추가
            }
        }

        /*// iOS 플랫폼 소스 세트 설정 추가
        val iosMain by getting {
            dependencies {
                // iOS 플랫폼 특정 의존성 추가
                // 예: implementation("com.example:ios-library:1.0.0")
            }
        }
        val iosX64Main by getting {
            dependsOn(iosMain)
        }
        val iosArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }*/
    }
}

android {
    namespace = "org.example.project.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

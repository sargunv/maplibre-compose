@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
  alias(libs.plugins.kotlin.multiplatform)
  alias(libs.plugins.kotlin.cocoapods)
  alias(libs.plugins.android.library)
  alias(libs.plugins.jetbrains.compose)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.spotless)
}

version = "0.1.0"

kotlin {
  explicitApi()

  androidTarget { compilerOptions { jvmTarget.set(JvmTarget.JVM_11) } }
  iosArm64()
  iosSimulatorArm64()
  iosX64()

  cocoapods {
    noPodspec()
    ios.deploymentTarget = "12.0"
    pod("MapLibre", libs.versions.maplibre.ios.get())
  }

  sourceSets {
    androidMain.dependencies {
      implementation(libs.maplibre.android)
      implementation(libs.maplibre.android.plugin.annotation)
    }
    commonMain.dependencies {
      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.ui)
    }
    commonTest.dependencies { implementation(libs.kotlin.test) }
  }
}

android {
  namespace = "dev.sargunv.maplibrecompose"
  compileSdk = libs.versions.android.compileSdk.get().toInt()

  defaultConfig { minSdk = libs.versions.android.minSdk.get().toInt() }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
}

spotless {
  kotlin {
    target("src/**/*.kt")
    ktfmt().googleStyle()
  }
}
@file:Suppress("UnstableApiUsage")

rootProject.name = "maplibre-compose"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
  repositories {
    google {
      mavenContent {
        includeGroupAndSubgroups("androidx")
        includeGroupAndSubgroups("com.android")
        includeGroupAndSubgroups("com.google")
      }
    }
    mavenCentral()
    gradlePluginPortal()
  }
}

dependencyResolutionManagement {
  repositories {
    google {
      mavenContent {
        includeGroupAndSubgroups("androidx")
        includeGroupAndSubgroups("com.android")
        includeGroupAndSubgroups("com.google")
      }
    }
    mavenCentral()
    // https://kevinnzou.github.io/compose-webview-multiplatform/installation/
    maven("https://jogamp.org/deployment/maven")
  }
}

include(":lib:maplibre-compose", ":lib:maplibre-compose-material3", ":lib", ":demo-app", ":")

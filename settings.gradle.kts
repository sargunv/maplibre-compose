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
  }
}

include(
  ":",
  ":demo-app",
  ":lib",
  ":lib:maplibre-compose",
  ":lib:maplibre-compose-material3",
  ":lib:maplibre-compose-expressions",
  ":lib:kotlin-maplibre-js",
  ":lib:compose-html-interop",
)

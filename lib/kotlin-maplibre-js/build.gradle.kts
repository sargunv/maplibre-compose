plugins {
  id("library-conventions")
  id(libs.plugins.kotlin.multiplatform.get().pluginId)
  id(libs.plugins.mavenPublish.get().pluginId)
}

mavenPublishing {
  pom {
    name = "MapLibre GL JS Kotlin"
    description = "Kotlin wrapper for MapLibre GL JS."
    url = "https://github.com/sargunv/maplibre-compose"
  }
}

kotlin {
  js(IR) {
    browser {}
    useEsModules()
    binaries.executable()
    generateTypeScriptDefinitions()
  }

  sourceSets {
    commonMain.dependencies {
      implementation(kotlin("stdlib-js"))
      implementation(npm("maplibre-gl", "4.7.1"))
    }

    commonTest.dependencies {
      implementation(kotlin("test"))
      implementation(kotlin("test-common"))
      implementation(kotlin("test-annotations-common"))
    }
  }
}

val jsBrowserDistribution by
  configurations.registering {
    isCanBeConsumed = true
    isCanBeResolved = false
  }

artifacts.add(
  jsBrowserDistribution.name,
  tasks.named("jsBrowserDistribution").map { it.outputs.files.files.single() },
)

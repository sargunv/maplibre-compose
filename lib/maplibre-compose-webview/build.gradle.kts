plugins {
  id("module-conventions")
  id(libs.plugins.kotlin.multiplatform.get().pluginId)
}

kotlin {
  js(IR) {
    browser { webpackTask {} }

    useEsModules()
    binaries.executable()
  }

  sourceSets {
    commonMain.dependencies {
      implementation(kotlin("stdlib-js"))
      implementation(project(":lib:kotlin-maplibre-js"))

      implementation(devNpm("html-webpack-plugin", "5.6.3"))
      implementation(devNpm("html-inline-script-webpack-plugin", "3.2.1"))
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

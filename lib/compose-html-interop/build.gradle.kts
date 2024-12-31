plugins {
  id("library-conventions")
  id(libs.plugins.kotlin.multiplatform.get().pluginId)
  id(libs.plugins.kotlin.composeCompiler.get().pluginId)
  id(libs.plugins.compose.get().pluginId)
  id(libs.plugins.mavenPublish.get().pluginId)
}

mavenPublishing {
  pom {
    name = "Compose HTML Interop"
    description = "Include an HTML element in a Compose Web UI."
    url = "https://github.com/sargunv/maplibre-compose"
  }
}

kotlin {
  js(IR) { browser() }

  sourceSets {
    commonMain.dependencies {
      implementation(kotlin("stdlib-js"))
      implementation(compose.foundation)
    }

    commonTest.dependencies {
      implementation(kotlin("test"))
      implementation(kotlin("test-common"))
      implementation(kotlin("test-annotations-common"))
    }
  }
}

plugins {
  id("library-conventions")
  id(libs.plugins.kotlin.multiplatform.get().pluginId)
  id(libs.plugins.mavenPublish.get().pluginId)
}

mavenPublishing {
  pom {
    name = "MapLibre Native Kotlin"
    description = "Kotlin bindings for MapLibre Native."
    url = "https://github.com/sargunv/maplibre-compose"
  }
}

kotlin {
  jvm()
  macosX64()
  macosArm64()
  iosSimulatorArm64()
  iosX64()
  iosArm64()

  sourceSets {
    commonMain.dependencies {}

    commonTest.dependencies {
      implementation(kotlin("test"))
      implementation(kotlin("test-common"))
      implementation(kotlin("test-annotations-common"))
    }

    jvmMain {
      dependencies {
        implementation("org.scijava:native-lib-loader:2.5.0")
        implementation("com.github.jnr:jnr-ffi:2.2.17")
      }
    }
  }
}

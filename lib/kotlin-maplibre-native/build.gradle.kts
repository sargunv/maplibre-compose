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

val cmakeGenerate by
  tasks.registering(Exec::class) {
    val nativesDir = project.layout.projectDirectory.dir("natives")
    val cmakeDir = project.layout.buildDirectory.dir("cmake")

    inputs.file(nativesDir.file("CMakeLists.txt"))
    outputs.dir(cmakeDir)

    workingDir(nativesDir)
    mkdir(cmakeDir)
    commandLine("/usr/bin/env", "cmake", "-S", nativesDir, "-B", cmakeDir.get())
  }

val cmakeBuild by
  tasks.registering(Exec::class) {
    val nativesDir = project.layout.projectDirectory.dir("natives")
    val cmakeDir = cmakeGenerate.map { it.outputs.files.singleFile }

    inputs.dir(nativesDir)
    inputs.dir(cmakeDir)
    outputs.dir(cmakeDir)

    workingDir(nativesDir)
    commandLine("/usr/bin/env", "cmake", "--build", cmakeDir.get(), "-j")
  }

val prepareJvmNatives by
  tasks.registering(Copy::class) {
    from(
      cmakeBuild.map {
        it.outputs.files.asFileTree.matching { include("libkotlin-maplibre-native.*") }
      }
    )
    eachFile { path = "natives/osx_arm64/$path" }
    into(project.layout.buildDirectory.dir("jvmNatives"))
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
      resources.srcDir(prepareJvmNatives.map { it.destinationDir })
      dependencies {
        implementation("org.scijava:native-lib-loader:2.5.0")
        implementation("com.github.jnr:jnr-ffi:2.2.17")
      }
    }
  }
}

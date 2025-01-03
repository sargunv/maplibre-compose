import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

fun Project.getJvmTarget(): JvmTarget {
  val target = properties["jvmTarget"]!!.toString().toInt()
  return JvmTarget.valueOf("JVM_$target")
}

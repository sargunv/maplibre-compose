package dev.sargunv.maplibrecompose.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.SwingPanel
import co.touchlab.kermit.Logger
import dev.sargunv.maplibrecompose.core.MaplibreMap
import dev.sargunv.maplibrenative.ClientOptions
import dev.sargunv.maplibrenative.ResourceOptions
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL.createCapabilities
import org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT
import org.lwjgl.opengl.GL11.GL_QUADS
import org.lwjgl.opengl.GL11.glBegin
import org.lwjgl.opengl.GL11.glClear
import org.lwjgl.opengl.GL11.glClearColor
import org.lwjgl.opengl.GL11.glColor3f
import org.lwjgl.opengl.GL11.glEnd
import org.lwjgl.opengl.GL11.glVertex2f
import org.lwjgl.opengl.GL11.glViewport
import org.lwjgl.opengl.awt.AWTGLCanvas
import javax.swing.SwingUtilities
import kotlin.math.abs
import kotlin.math.sin

@Composable
internal actual fun ComposableMapView(
  modifier: Modifier,
  styleUri: String,
  update: (map: MaplibreMap) -> Unit,
  onReset: () -> Unit,
  logger: Logger?,
  callbacks: MaplibreMap.Callbacks,
) =
  DesktopMapView(
    modifier = modifier,
    styleUri = styleUri,
    update = update,
    onReset = onReset,
    logger = logger,
    callbacks = callbacks,
  )

@Composable
internal fun DesktopMapView(
  modifier: Modifier,
  styleUri: String,
  update: suspend (map: MaplibreMap) -> Unit,
  onReset: () -> Unit,
  logger: Logger?,
  callbacks: MaplibreMap.Callbacks,
) {
  DisposableEffect(Unit) {
    val clientOptions =
      ClientOptions().apply {
        name = "MaplibreCompose"
        version = "0.1.0"
      }
    val resourceOptions = ResourceOptions()
    onDispose {
      println("Disposing $clientOptions")
      println("Disposing $resourceOptions")
      resourceOptions.close()
      clientOptions.close()
    }
  }
  SwingPanel(modifier = modifier.fillMaxSize(), factory = { OpenGlDemo() }, update = { _ -> })
}

internal class OpenGlDemo : AWTGLCanvas() {
  init {
    SwingUtilities.invokeLater(RenderLoop(this))
  }

  override fun initGL() {
    createCapabilities()
    glClearColor(0.3f, 0.4f, 0.5f, 1f)
  }

  override fun paintGL() {
    glClear(GL_COLOR_BUFFER_BIT)
    glViewport(0, 0, framebufferWidth, framebufferHeight)

    val aspect = framebufferWidth.toFloat() / framebufferHeight
    val shapeWidth = abs(sin(System.currentTimeMillis() * 0.001 * 0.3)).toFloat()
    glBegin(GL_QUADS)
    glColor3f(0.4f, 0.6f, 0.8f)
    glVertex2f(-0.75f * shapeWidth / aspect, 0.0f)
    glVertex2f(0f, -0.75f)
    glVertex2f(+0.75f * shapeWidth / aspect, 0f)
    glVertex2f(0f, +0.75f)
    glEnd()

    swapBuffers()
  }

  class RenderLoop(private val demo: OpenGlDemo) : Runnable {
    override fun run() {
      if (demo.isValid) {
        demo.render()
        SwingUtilities.invokeLater(this)
      } else {
        GL.setCapabilities(null)
      }
    }
  }
}

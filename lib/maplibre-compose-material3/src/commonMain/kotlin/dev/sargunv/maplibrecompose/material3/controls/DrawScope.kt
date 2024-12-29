package dev.sargunv.maplibrecompose.material3.controls

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope.Companion.DefaultBlendMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText

internal fun DrawScope.drawPolyline(
  color: Color,
  polyline: List<Offset>,
  strokeWidth: Float = Stroke.HairlineWidth,
  cap: StrokeCap = Stroke.DefaultCap,
  pathEffect: PathEffect? = null,
  alpha: Float = 1.0f,
  colorFilter: ColorFilter? = null,
  blendMode: BlendMode = DefaultBlendMode
) {
  val it = polyline.iterator()
  if (!it.hasNext()) return
  var start = Offset.Zero
  val first = it.next()
  while (it.hasNext()) {
    val end = it.next()
    drawLine(
      color = color,
      start = first + start,
      end = first + end,
      strokeWidth = strokeWidth,
      cap = cap,
      pathEffect = pathEffect,
      alpha = alpha,
      colorFilter = colorFilter,
      blendMode = blendMode
    )
    start = end
  }
}

internal fun DrawScope.drawPolylinesWithHalo(
  color: Color,
  haloColor: Color,
  polylines: List<List<Offset>>,
  strokeWidth: Float = Stroke.HairlineWidth,
  haloWidth: Float = Stroke.HairlineWidth,
  cap: StrokeCap = Stroke.DefaultCap,
) {
  for (polyline in polylines) {
    drawPolyline(
      color = haloColor,
      polyline = polyline,
      strokeWidth = strokeWidth + haloWidth * 2,
      cap = cap,
    )
  }
  for (polyline in polylines) {
    drawPolyline(
      color = color,
      polyline = polyline,
      strokeWidth = strokeWidth,
      cap = cap,
    )
  }
}

internal fun DrawScope.drawTextWithHalo(
  textLayoutResult: TextLayoutResult,
  topLeft: Offset = Offset.Zero,
  color: Color = Color.Unspecified,
  haloColor: Color = Color.Unspecified,
  haloWidth: Float = 0f,
) {
  // * 2 because the stroke is painted half outside and half inside of the text shape
  val stroke = Stroke(width = haloWidth * 2, cap = StrokeCap.Round, join = StrokeJoin.Round)
  drawText(
    textLayoutResult = textLayoutResult,
    color = haloColor,
    topLeft = topLeft,
    drawStyle = stroke
  )
  drawText(
    textLayoutResult = textLayoutResult,
    color = color,
    topLeft = topLeft,
  )
}

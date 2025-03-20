package dev.sargunv.maplibrecompose.core.source

import android.text.Html
import android.text.style.URLSpan
import org.maplibre.android.style.sources.Source as MLNSource

public actual sealed class Source {
  internal abstract val impl: MLNSource

  internal actual val id: String by lazy { impl.id }

  public actual val attributionLinks: List<AttributionLink> by lazy {
    // TODO minSdk 24 to get rid of deprecation warning
    @Suppress("DEPRECATION") val spanned = Html.fromHtml(impl.attribution)

      val spans = spanned.getSpans(0, spanned.length, URLSpan::class.java)
      spans.map {
        AttributionLink(
          title = spanned.slice(spanned.getSpanStart(it)..<spanned.getSpanEnd(it)).toString(),
          url = it.url,
        )
      }
    }

  override fun toString(): String = "${this::class.simpleName}(id=\"$id\")"
}

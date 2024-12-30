package dev.sargunv.maplibrecompose.demoapp

expect object Platform {
  val supportsBlending: Boolean
  val supportsFps: Boolean
  val supportsCamera: Boolean
  val supportsLayers: Boolean
}

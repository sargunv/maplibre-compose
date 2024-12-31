[![Github Actions](https://github.com/sargunv/maplibre-compose/actions/workflows/ci.yml/badge.svg)](https://github.com/sargunv/maplibre-compose/actions/workflows/ci.yml?query=branch%3Amain)
[![Maven Central Version](https://img.shields.io/maven-central/v/dev.sargunv.maplibre-compose/maplibre-compose)](https://central.sonatype.com/namespace/dev.sargunv.maplibre-compose)
[![License](https://img.shields.io/github/license/sargunv/maplibre-compose)](https://github.com/sargunv/maplibre-compose/blob/main/LICENSE)
[![Documentation](https://img.shields.io/badge/Documentation-blue?logo=MaterialForMkDocs&logoColor=white)](https://sargunv.github.io/maplibre-compose/)
[![API Reference](https://img.shields.io/badge/API_Reference-blue?logo=Kotlin&logoColor=white)](https://sargunv.github.io/maplibre-compose/api/)

# MapLibre for Compose

This project is a
[Compose Multiplatform](https://www.jetbrains.com/compose-multiplatform/)
library for [MapLibre](https://maplibre.org/). You can use it to embed an
interactive vector map in your Compose app.

<p float="left">
  <img src="https://github.com/user-attachments/assets/997cf8a4-2841-40c8-b5a1-ef98193b21b2" width="200"/>
  <img src="https://github.com/user-attachments/assets/e450f689-e254-48b7-bd91-3d3042faa290" width="200"/>
</p>

## Usage

See the
[Getting Started guide](https://sargunv.github.io/maplibre-compose/getting-started/)
in the documentation.

## Status

Many common use cases are already supported, but the full breadth of the
MapLibre SDKs is not yet covered. What is already supported may have bugs. API
stability is not yet guaranteed; as we're still exploring how best to express an
interactive map API in Compose.

Android and iOS support is implemented with
[MapLibre Native](https://github.com/maplibre/maplibre-native). A broad set of
features are supported.

Web support is implemented with
[MapLibre GL JS](https://github.com/maplibre/maplibre-gl-js).

Desktop support is also implemented with MapLibre GL JS and
[KCEF](https://github.com/DatL4g/KCEF) for now, though we'd like to switch to
MapLibre Native.

Web is not yet supported.

[![Maven Central Version](https://img.shields.io/maven-central/v/dev.sargunv.maplibre-compose/maplibre-compose?label=Maven)](https://central.sonatype.com/namespace/dev.sargunv.maplibre-compose)
[![License](https://img.shields.io/github/license/maplibre/maplibre-compose?label=License)](https://github.com/maplibre/maplibre-compose/blob/main/LICENSE)
[![Kotlin Version](https://img.shields.io/badge/dynamic/toml?url=https%3A%2F%2Fraw.githubusercontent.com%2Fmaplibre%2Fmaplibre-compose%2Frefs%2Fheads%2Fmain%2Fgradle%2Flibs.versions.toml&query=versions.gradle-kotlin&prefix=v&logo=kotlin&label=Kotlin)](./gradle/libs.versions.toml)
[![Compose Version](https://img.shields.io/badge/dynamic/toml?url=https%3A%2F%2Fraw.githubusercontent.com%2Fmaplibre%2Fmaplibre-compose%2Frefs%2Fheads%2Fmain%2Fgradle%2Flibs.versions.toml&query=versions.gradle-compose&prefix=v&logo=jetpackcompose&label=Compose)](./gradle/libs.versions.toml)
[![Documentation](https://img.shields.io/badge/Documentation-blue?logo=MaterialForMkDocs&logoColor=white)](https://maplibre.org/maplibre-compose/)
[![API Reference](https://img.shields.io/badge/API_Reference-blue?logo=Kotlin&logoColor=white)](https://maplibre.org/maplibre-compose/api/)
[![Slack](https://img.shields.io/badge/Slack-4A154B?logo=slack&logoColor=white)](https://osmus.slack.com/archives/maplibre-compose)

# MapLibre for Compose Multiplatform

## Introduction

MapLibre Compose is a [Compose Multiplatform][compose] wrapper around the
[MapLibre][maplibre] SDKs for rendering interactive maps. You can use it to add
maps to your Compose UIs across Android, iOS, Desktop, and Web.

<p float="left">
  <img src="https://github.com/user-attachments/assets/997cf8a4-2841-40c8-b5a1-ef98193b21b2" width="200" alt="iOS Screenshot"/>
  <img src="https://github.com/user-attachments/assets/e450f689-e254-48b7-bd91-3d3042faa290" width="200" alt="Android Screenshot"/>
</p>

## Usage

- [Getting Started](https://maplibre.org/maplibre-compose/getting-started/)
- [API Reference](https://maplibre.org/maplibre-compose/api/)
- [Demo App](./demo-app)

## Status

A large subset of MapLibre's features are already supported, but the full
breadth of the MapLibre SDKs is not yet covered. What is already supported may
have bugs. API stability is not yet guaranteed; we're still exploring how best
to express an interactive map API in Compose.

See [the status table][status] for a breakdown of supported features on each
platform. Android and iOS have the most complete support, while Desktop and Web
are still catching up.

[compose]: https://www.jetbrains.com/compose-multiplatform/
[maplibre]: https://maplibre.org/
[status]: https://maplibre.org/maplibre-compose/#status

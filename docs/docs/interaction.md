# Interacting with the map

## Gestures

The map supports pan, zoom, rotate, and tilt gestures. Each of these can be
enabled or disabled individually:

```kotlin
-8<- "demo-app/src/commonMain/kotlin/dev/sargunv/maplibrecompose/demoapp/docs/Interaction.kt:gesture-settings"
```

1. Includes pinch, double-tap, and double-tap-and-drag.

## Overlays

### Ornaments

Ornaments are built in UI elements that are displayed on the map, such as a
compass or attribution button. They're implemented by the underlying MapLibre
SDK, so may render differently on different platforms. You can control the
visibility and position of these ornaments:

```kotlin
-8<- "demo-app/src/commonMain/kotlin/dev/sargunv/maplibrecompose/demoapp/docs/Interaction.kt:ornament-settings"
```

1. Insets the ornaments; useful if you have an edge-to-edge map or some UI
   elements that cover part of the map.
2. Displays a MapLibre logo
3. Possible alignments are constrained by the underlying SDK. The four corners
   are supported across platforms.
4. Displays attribution defined in the map style.
5. Displays a compass control when the map is rotated away from north.
6. Displays a scale control showing the distance represented by the map's zoom
   level.

### Material 3 Controls

We also provide reimplementations of certain ornaments using Material 3. These
are regular Compose UI components, so you can position them arbitrarily, style
them, animate them, etc. To use them, disable the corresponding built-in
ornament and add the Material control to the map's `overlay`:

TODO add example

## Camera

If you want to read or mutate the camera state, use `rememberCameraState()`. You
can use this to set the start position of the map:

```kotlin
-8<- "demo-app/src/commonMain/kotlin/dev/sargunv/maplibrecompose/demoapp/docs/Interaction.kt:camera"
```

You can now use the `camera` reference to move the camera. For example,
`CameraState` exposes a `suspend fun animateTo` to animate the camera to a new
position:

```kotlin
-8<- "demo-app/src/commonMain/kotlin/dev/sargunv/maplibrecompose/demoapp/docs/Interaction.kt:camera-animate"
```

## Click listeners

You can listen for clicks on the map. Given a click location, you can use camera
state to query which features are present at that location:

```kotlin
-8<- "demo-app/src/commonMain/kotlin/dev/sargunv/maplibrecompose/demoapp/docs/Interaction.kt:click-listeners"
```

1. Consumes the click event, preventing it from propagating to the individual
   layers' click listeners.

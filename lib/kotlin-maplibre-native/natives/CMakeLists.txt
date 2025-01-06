cmake_minimum_required(VERSION 3.30 FATAL_ERROR)
include(cmake/CPM.cmake)

project(kotlin-maplibre-native)

find_package(Java REQUIRED)
include_directories(${JAVA_INCLUDE_PATH})
include_directories(${JAVA_INCLUDE_PATH2})

find_package(JNI REQUIRED)
include_directories(${JNI_INCLUDE_DIRS})

CPMAddPackage("gh:maplibre/maplibre-native#android-v11.7.1")

include_directories(includes)

add_library(${PROJECT_NAME} SHARED ClientOptions.cpp)

target_link_libraries(${PROJECT_NAME} PRIVATE mbgl-core)

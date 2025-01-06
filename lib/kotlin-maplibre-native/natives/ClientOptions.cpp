#include "mbgl/util/client_options.hpp"

#include <iostream>

extern "C" {
void *ClientOptions_new(const char *name, const char *version) {
  auto *options = new mbgl::ClientOptions();
  options->withName(name).withVersion(version);
  return options;
}

void ClientOptions_delete(void *nativePtr) {
  delete static_cast<mbgl::ClientOptions *>(nativePtr);
}

const char *ClientOptions_name(void *nativePtr) {
  return static_cast<mbgl::ClientOptions *>(nativePtr)->name().c_str();
}

const char *ClientOptions_version(void *nativePtr) {
  return static_cast<mbgl::ClientOptions *>(nativePtr)->version().c_str();
}
}

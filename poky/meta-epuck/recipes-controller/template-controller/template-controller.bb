DESCRIPTION = "Template controller"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=77f719528c8e739b3940a5574b90f6f9"

DEPENDS = "argos3-epuck"

SRC_URI = "file://template-controller"

S = "${WORKDIR}/template-controller"

EXTRA_OECMAKE = "-DARGOS_BUILD_FOR=epuck -DARGOS_INCLUDE_DIRS=${STAGING_DIR_HOST}/usr/include/ -DARGOS_LIBRARY_DIRS=${STAGING_DIR_HOST}/usr/lib/argos3"

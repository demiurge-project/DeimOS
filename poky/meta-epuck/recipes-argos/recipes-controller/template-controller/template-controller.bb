DESCRIPTION = "Template controller"

LICENSE = "CLOSED"
BB_STRICT_CHECKSUM = "0"

DEPENDS = "argos3-epuck"

SRC_URI = "file://template-controller"

S = "${WORKDIR}/template-controller"

EXTRA_OECMAKE = "-DARGOS_BUILD_FOR=epuck -DARGOS_INCLUDE_DIRS=${STAGING_DIR_HOST}/usr/include/ -DARGOS_LIBRARY_DIRS=${STAGING_DIR_HOST}/usr/lib/argos3"
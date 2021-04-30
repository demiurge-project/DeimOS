DESCRIPTION = "Template controller"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7c1b8e0e670d3d0908d5bbc329198ed7"

DEPENDS = "argos3-epuck"

SRC_URI = "file://template-controller"

S = "${WORKDIR}/template-controller"

EXTRA_OECMAKE = "-DARGOS_BUILD_FOR=epuck -DARGOS_INCLUDE_DIRS=${STAGING_DIR_HOST}/usr/include/ -DARGOS_LIBRARY_DIRS=${STAGING_DIR_HOST}/usr/lib/argos3"
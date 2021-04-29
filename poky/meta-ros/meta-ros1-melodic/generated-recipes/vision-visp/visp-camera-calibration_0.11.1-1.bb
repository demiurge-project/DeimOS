# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "visp_camera_calibration allows easy calibration of      cameras using a customizable pattern and ViSP library."
AUTHOR = "Fabien Spindler <Fabien.Spindler@inria.fr>"
ROS_AUTHOR = "Filip Novotny"
HOMEPAGE = "http://wiki.ros.org/visp_camera_calibration"
SECTION = "devel"
LICENSE = "GPL-2"
LIC_FILES_CHKSUM = "file://package.xml;beginline=12;endline=12;md5=fe8b75cf0aba647401e1038bcd69ee74"

ROS_CN = "vision_visp"
ROS_BPN = "visp_camera_calibration"

ROS_BUILD_DEPENDS = " \
    camera-calibration-parsers \
    geometry-msgs \
    message-generation \
    roscpp \
    sensor-msgs \
    std-msgs \
    visp \
    visp-bridge \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    camera-calibration-parsers \
    geometry-msgs \
    message-generation \
    message-runtime \
    roscpp \
    sensor-msgs \
    std-msgs \
    visp \
    visp-bridge \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    camera-calibration-parsers \
    geometry-msgs \
    message-generation \
    message-runtime \
    roscpp \
    sensor-msgs \
    std-msgs \
    visp \
    visp-bridge \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/lagadic/vision_visp-release/archive/release/melodic/visp_camera_calibration/0.11.1-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/visp_camera_calibration"
SRC_URI = "git://github.com/lagadic/vision_visp-release;${ROS_BRANCH};protocol=https"
SRCREV = "c107ccfc7afec03ff1e2d0d38e1d70f272d714cb"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}

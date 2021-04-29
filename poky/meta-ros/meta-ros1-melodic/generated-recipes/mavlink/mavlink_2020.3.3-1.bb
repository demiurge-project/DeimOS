# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "MAVLink message marshaling library.   This package provides C-headers and C++11 library   for both 1.0 and 2.0 versions of protocol.    For pymavlink use separate install via rosdep (python-pymavlink)."
AUTHOR = "Vladimir Ermakov <vooon341@gmail.com>"
ROS_AUTHOR = "Lorenz Meier"
HOMEPAGE = "https://mavlink.io/en/"
SECTION = "devel"
LICENSE = "LGPL-2"
LIC_FILES_CHKSUM = "file://package.xml;beginline=20;endline=20;md5=b691248d2f70cdaeeaf13696ada5d47c"

ROS_CN = "mavlink"
ROS_BPN = "mavlink"

ROS_BUILD_DEPENDS = " \
    ${PYTHON_PN}-future \
    ${PYTHON_PN}-lxml \
    python \
"

ROS_BUILDTOOL_DEPENDS = " \
    cmake-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    catkin \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/mavlink/mavlink-gbp-release/archive/release/melodic/mavlink/2020.3.3-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/mavlink"
SRC_URI = "git://github.com/mavlink/mavlink-gbp-release;${ROS_BRANCH};protocol=https"
SRCREV = "e8962e7c66b76d2ad0ac883914161cf0ea5cab40"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "cmake"

inherit ros_${ROS_BUILD_TYPE}

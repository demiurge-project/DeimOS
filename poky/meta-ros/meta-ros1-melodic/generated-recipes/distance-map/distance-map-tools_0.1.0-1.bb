# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "Node package of the map_distance project.     It contains the ROS node that instantiate the     distance map plugin."
AUTHOR = "Jeremie Deray <deray.jeremie@gmail.com>"
ROS_AUTHOR = "Jeremie Deray <deray.jeremie@gmail.com>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=14;endline=14;md5=82f0323c08605e5b6f343b05213cf7cc"

ROS_CN = "distance_map"
ROS_BPN = "distance_map_tools"

ROS_BUILD_DEPENDS = " \
    backward-ros \
    distance-map-core \
    interactive-markers \
    visualization-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    backward-ros \
    distance-map-core \
    interactive-markers \
    visualization-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    backward-ros \
    distance-map-core \
    interactive-markers \
    visualization-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/artivis/distance_map-release/archive/release/melodic/distance_map_tools/0.1.0-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/distance_map_tools"
SRC_URI = "git://github.com/artivis/distance_map-release;${ROS_BRANCH};protocol=https"
SRCREV = "5b128c42f96bb1c9e7b790a247cdfe2069c3a41b"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}

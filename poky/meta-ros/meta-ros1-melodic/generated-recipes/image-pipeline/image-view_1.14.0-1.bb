# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "A simple viewer for ROS image topics. Includes a specialized viewer   for stereo + disparity images."
AUTHOR = "Vincent Rabaud <vincent.rabaud@gmail.com>"
ROS_AUTHOR = "Patrick Mihelich"
HOMEPAGE = "http://www.ros.org/wiki/image_view"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=12;endline=12;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "image_pipeline"
ROS_BPN = "image_view"

ROS_BUILD_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_gtk2} \
    camera-calibration-parsers \
    cv-bridge \
    dynamic-reconfigure \
    image-transport \
    message-filters \
    message-generation \
    nodelet \
    rosconsole \
    roscpp \
    sensor-msgs \
    std-srvs \
    stereo-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_gtk2} \
    camera-calibration-parsers \
    cv-bridge \
    dynamic-reconfigure \
    image-transport \
    message-filters \
    nodelet \
    rosconsole \
    roscpp \
    std-srvs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_gtk2} \
    camera-calibration-parsers \
    cv-bridge \
    dynamic-reconfigure \
    image-transport \
    message-filters \
    nodelet \
    rosconsole \
    roscpp \
    std-srvs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    rostest \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros-gbp/image_pipeline-release/archive/release/melodic/image_view/1.14.0-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/image_view"
SRC_URI = "git://github.com/ros-gbp/image_pipeline-release;${ROS_BRANCH};protocol=https"
SRCREV = "e37e50fc4ee90fe97192c8d5f29598b9182643fa"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}

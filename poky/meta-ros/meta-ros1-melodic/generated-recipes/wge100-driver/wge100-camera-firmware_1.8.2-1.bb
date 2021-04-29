# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "Source for the WGE100 Ethernet camera: Verilog source for the     FPGA, Forth source for the camera firmware.  Intended for camera     developers.  Note that a built binary from this package is checked     in under wge100_camera/firmware_images/"
AUTHOR = "Austin Hendrix <ahendrix@willowgarage.com>"
ROS_AUTHOR = "James Bowman"
HOMEPAGE = "http://ros.org/wiki/wge100_camera_firmware"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=16;endline=16;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "wge100_driver"
ROS_BPN = "wge100_camera_firmware"

ROS_BUILD_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_gforth} \
    rospy \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_gforth} \
    rospy \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_gforth} \
    rospy \
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

# matches with: https://github.com/ros-drivers-gbp/wge100_driver-release/archive/release/melodic/wge100_camera_firmware/1.8.2-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/wge100_camera_firmware"
SRC_URI = "git://github.com/ros-drivers-gbp/wge100_driver-release;${ROS_BRANCH};protocol=https"
SRCREV = "d5d324d001c68328f1ac42e179994125b962c376"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}

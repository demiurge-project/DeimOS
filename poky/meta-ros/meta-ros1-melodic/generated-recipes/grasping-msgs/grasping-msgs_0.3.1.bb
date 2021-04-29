# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "Messages for describing objects and how to grasp them."
AUTHOR = "Michael Ferguson <mfergs7@gmail.com>"
ROS_AUTHOR = "Michael Ferguson"
HOMEPAGE = "http://ros.org/wiki/grasping_msgs"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=10;endline=10;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "grasping_msgs"
ROS_BPN = "grasping_msgs"

ROS_BUILD_DEPENDS = " \
    actionlib \
    geometry-msgs \
    message-generation \
    moveit-msgs \
    sensor-msgs \
    shape-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    actionlib \
    geometry-msgs \
    message-runtime \
    moveit-msgs \
    sensor-msgs \
    shape-msgs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    actionlib \
    geometry-msgs \
    message-runtime \
    moveit-msgs \
    sensor-msgs \
    shape-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/mikeferguson/grasping_msgs-gbp/archive/release/melodic/grasping_msgs/0.3.1-0.tar.gz
ROS_BRANCH ?= "branch=release/melodic/grasping_msgs"
SRC_URI = "git://github.com/mikeferguson/grasping_msgs-gbp;${ROS_BRANCH};protocol=https"
SRCREV = "6257644360e3628d629100d8a15725f5411bf8e4"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}

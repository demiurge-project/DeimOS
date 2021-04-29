# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "This package provides a kinematics implementation for the PR2 robot. It can be used to compute forward and inverse kinematics."
AUTHOR = "ROS Orphaned Package Maintainers <ros-orphaned-packages@googlegroups.com>"
ROS_AUTHOR = "Sachin Chitta"
HOMEPAGE = "http://ros.org/wiki/pr2_arm_kinematics"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=7;endline=7;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "pr2_kinematics"
ROS_BPN = "pr2_arm_kinematics"

ROS_BUILD_DEPENDS = " \
    angles \
    cmake-modules \
    geometry-msgs \
    kdl-parser \
    moveit-core \
    moveit-msgs \
    pluginlib \
    roscpp \
    tf-conversions \
    urdf \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    angles \
    geometry-msgs \
    kdl-parser \
    moveit-core \
    moveit-msgs \
    pluginlib \
    roscpp \
    tf-conversions \
    urdf \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    angles \
    geometry-msgs \
    kdl-parser \
    moveit-core \
    moveit-msgs \
    pluginlib \
    roscpp \
    tf-conversions \
    urdf \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/pr2-gbp/pr2_kinematics-release/archive/release/melodic/pr2_arm_kinematics/1.0.10-0.tar.gz
ROS_BRANCH ?= "branch=release/melodic/pr2_arm_kinematics"
SRC_URI = "git://github.com/pr2-gbp/pr2_kinematics-release;${ROS_BRANCH};protocol=https"
SRCREV = "d5dd822b7972130cc667c2797a83f04f2650cc8d"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}

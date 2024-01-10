# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "This package contains the python bindings PyKDL for the Kinematics and Dynamics     Library (KDL), distributed by the Orocos Project."
AUTHOR = "Ruben Smits <ruben@intermodalics.eu>"
HOMEPAGE = "http://wiki.ros.org/python_orocos_kdl"
SECTION = "devel"
LICENSE = "LGPL-2"
LIC_FILES_CHKSUM = "file://package.xml;beginline=10;endline=10;md5=46ee8693f40a89a31023e97ae17ecf19"

ROS_CN = "orocos_kinematics_dynamics"
ROS_BPN = "python_orocos_kdl"

ROS_BUILD_DEPENDS = " \
    orocos-kdl \
    sip \
"

ROS_BUILDTOOL_DEPENDS = " \
    cmake-native \
"

ROS_EXPORT_DEPENDS = " \
    catkin \
    orocos-kdl \
    sip \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    catkin \
    orocos-kdl \
    sip \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/orocos/orocos-kdl-release/archive/release/melodic/python_orocos_kdl/1.4.0-0.tar.gz
ROS_BRANCH ?= "branch=release/melodic/python_orocos_kdl"
SRC_URI = "git://github.com/orocos/orocos-kdl-release;${ROS_BRANCH};protocol=https \
	   file://0001-fix-compatibility-issues-with-sip-v4.19.23-and-later.patch \
	   "
SRCREV = "6995671810f03c03fce006d04883b6899ccece10"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "cmake"

inherit ros_${ROS_BUILD_TYPE}

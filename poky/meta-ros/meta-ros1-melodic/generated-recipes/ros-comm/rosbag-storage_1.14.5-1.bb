# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "This is a set of tools for recording from and playing back ROS     message without relying on the ROS client library."
AUTHOR = "Dirk Thomas <dthomas@osrfoundation.org>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=9;endline=9;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "ros_comm"
ROS_BPN = "rosbag_storage"

ROS_BUILD_DEPENDS = " \
    boost \
    bzip2 \
    console-bridge \
    cpp-common \
    gpgme \
    openssl \
    pluginlib \
    roscpp-serialization \
    roscpp-traits \
    roslz4 \
    rostest \
    rostime \
    std-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    boost \
    bzip2 \
    console-bridge \
    cpp-common \
    gpgme \
    openssl \
    pluginlib \
    roscpp-serialization \
    roscpp-traits \
    roslz4 \
    rostime \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    boost \
    bzip2 \
    console-bridge \
    cpp-common \
    gpgme \
    openssl \
    pluginlib \
    roscpp-serialization \
    roscpp-traits \
    roslz4 \
    rostime \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros-gbp/ros_comm-release/archive/release/melodic/rosbag_storage/1.14.5-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/rosbag_storage"
SRC_URI = "git://github.com/ros-gbp/ros_comm-release;${ROS_BRANCH};protocol=https"
SRCREV = "6b27bd918db478edce05e18edf180feb079dd43f"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}

# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "TRAC-IK is a faster, significantly more reliable drop-in replacement for     KDL's pseudoinverse Jacobian solver.      The TRAC-IK library has a very similar API to KDL's IK solver calls,     except that the user passes a maximum time instead of a maximum number of     search iterations.  Additionally, TRAC-IK allows for error tolerances to     be set independently for each Cartesian dimension (x,y,z,roll,pitch.yaw)."
AUTHOR = "Patrick Beeson <pbeeson@traclabs.com>"
ROS_AUTHOR = "Patrick Beeson"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=18;endline=18;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "trac_ik"
ROS_BPN = "trac_ik_lib"

ROS_BUILD_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_libnlopt-dev} \
    boost \
    cmake-modules \
    kdl-parser \
    libeigen \
    pkgconfig \
    roscpp \
    urdf \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_libnlopt-dev} \
    ${ROS_UNRESOLVED_PLATFORM_PKG_libnlopt0} \
    boost \
    kdl-parser \
    roscpp \
    urdf \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    ${ROS_UNRESOLVED_PLATFORM_PKG_libnlopt-dev} \
    ${ROS_UNRESOLVED_PLATFORM_PKG_libnlopt0} \
    boost \
    kdl-parser \
    roscpp \
    urdf \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/traclabs/trac_ik-release/archive/release/melodic/trac_ik_lib/1.5.1-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/trac_ik_lib"
SRC_URI = "git://github.com/traclabs/trac_ik-release;${ROS_BRANCH};protocol=https"
SRCREV = "15046ae7aff8d8b19c50c0d98f52a798f3b68214"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}

# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "Controller for executing joint-space trajectories on a group of joints."
AUTHOR = "Bence Magyar <bence.magyar.robotics@gmail.com>"
ROS_AUTHOR = "Adolfo Rodriguez Tsouroukdissian"
HOMEPAGE = "https://github.com/ros-controls/ros_controllers/wiki"
SECTION = "devel"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://package.xml;beginline=10;endline=10;md5=d566ef916e9dedc494f5f793a6690ba5"

ROS_CN = "ros_controllers"
ROS_BPN = "joint_trajectory_controller"

ROS_BUILD_DEPENDS = " \
    actionlib \
    angles \
    cmake-modules \
    control-msgs \
    control-toolbox \
    controller-interface \
    hardware-interface \
    pluginlib \
    realtime-tools \
    roscpp \
    trajectory-msgs \
    urdf \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    actionlib \
    angles \
    control-msgs \
    control-toolbox \
    controller-interface \
    hardware-interface \
    pluginlib \
    realtime-tools \
    roscpp \
    trajectory-msgs \
    urdf \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    actionlib \
    angles \
    control-msgs \
    control-toolbox \
    controller-interface \
    hardware-interface \
    pluginlib \
    realtime-tools \
    roscpp \
    trajectory-msgs \
    urdf \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    controller-manager \
    rostest \
    xacro \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/ros-gbp/ros_controllers-release/archive/release/melodic/joint_trajectory_controller/0.15.1-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/joint_trajectory_controller"
SRC_URI = "git://github.com/ros-gbp/ros_controllers-release;${ROS_BRANCH};protocol=https"
SRCREV = "7510286e41fe367ab202c6b4795d335b4a2db404"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}

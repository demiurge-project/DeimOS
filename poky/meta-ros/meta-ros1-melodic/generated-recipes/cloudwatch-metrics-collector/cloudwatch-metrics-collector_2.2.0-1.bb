# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "Subscriber node for the aws/monitoring topic to publish metrics to AWS Cloudwatch"
AUTHOR = "AWS RoboMaker <ros-contributions@amazon.com>"
ROS_AUTHOR = "AWS RoboMaker <ros-contributions@amazon.com>"
HOMEPAGE = "http://wiki.ros.org/cloudwatch_metrics_collector"
SECTION = "devel"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://package.xml;beginline=11;endline=11;md5=3dce4ba60d7e51ec64f3c3dc18672dd3"

ROS_CN = "cloudwatch_metrics_collector"
ROS_BPN = "cloudwatch_metrics_collector"

ROS_BUILD_DEPENDS = " \
    aws-common \
    aws-ros1-common \
    cloudwatch-metrics-common \
    ros-monitoring-msgs \
    roscpp \
    std-msgs \
    std-srvs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    aws-common \
    aws-ros1-common \
    cloudwatch-metrics-common \
    ros-monitoring-msgs \
    roscpp \
    std-msgs \
    std-srvs \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    aws-common \
    aws-ros1-common \
    cloudwatch-metrics-common \
    ros-monitoring-msgs \
    roscpp \
    std-msgs \
    std-srvs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = " \
    gtest \
    rostest \
"

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/aws-gbp/cloudwatch_metrics_collector-release/archive/release/melodic/cloudwatch_metrics_collector/2.2.0-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/cloudwatch_metrics_collector"
SRC_URI = "git://github.com/aws-gbp/cloudwatch_metrics_collector-release;${ROS_BRANCH};protocol=https"
SRCREV = "80c9fa606e270b6a85c0d628ad72471f429be8f8"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}

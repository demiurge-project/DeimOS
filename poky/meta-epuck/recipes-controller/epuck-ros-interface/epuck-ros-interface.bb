DESCRIPTION = "ROS interface controller installation"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://LICENSE;md5=77f719528c8e739b3940a5574b90f6f9"

inherit ros_distro_melodic
inherit ros_superflore_generated

DEPENDS = "argos3-epuck"

inherit ros_catkin

SRC_URI = "file://epuck-ros-interface"

S = "${WORKDIR}/epuck-ros-interface/src"

EXTRA_OECMAKE = "-DARGOS_BUILD_FOR=epuck -DARGOS_INCLUDE_DIRS=${STAGING_DIR_HOST}/usr/include/ -DARGOS_LIBRARY_DIRS=${STAGING_DIR_HOST}/usr/lib/argos3"

ROS_BUILD_DEPENDS = " \
	roscpp \
	message-generation \
	std-msgs \
	sensor-msgs \
	geometry-msgs \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    	roscpp \
	std-msgs \
	sensor-msgs \
	geometry-msgs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS += "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"






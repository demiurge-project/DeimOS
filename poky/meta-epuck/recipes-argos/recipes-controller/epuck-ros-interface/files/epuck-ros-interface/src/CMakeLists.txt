#
# Set minimum required version
#
cmake_minimum_required(VERSION 2.8)
project(epuck_ros_interface)

find_package(catkin REQUIRED COMPONENTS
  roscpp
  std_msgs
  sensor_msgs
  geometry_msgs
  message_generation
)

generate_messages(
  DEPENDENCIES
  std_msgs
  sensor_msgs
  geometry_msgs
)

catkin_package(
  LIBRARIES epuck_ros_interface
  CATKIN_DEPENDS message_runtime roscpp std_msgs sensor_msgs geometry_msgs
  DEPENDS system_lib
)

set(CATKIN_DEVEL_PREFIX /home/gumstix)

include_directories(
  ${catkin_INCLUDE_DIRS}
)

link_libraries(${catkin_LIBRARIES})

#
# Set the path additional cmake files must be searched for
#
set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} ${CMAKE_SOURCE_DIR}/cmake)

#
# Make sure we are under Unix
#
if(NOT UNIX)
  message(FATAL_ERROR "ARGoS compiles only under UNIX, sorry!")
endif(NOT UNIX)

#
# Set build options
#
include(${CMAKE_SOURCE_DIR}/cmake/ARGoSBuildOptions.cmake)

#
# Set compiler flags
#
include(${CMAKE_SOURCE_DIR}/cmake/ARGoSBuildFlags.cmake)

#
# Check for libraries
#
include(${CMAKE_SOURCE_DIR}/cmake/ARGoSBuildChecks.cmake)

#
# Set up CPack for later use
#
include(${CMAKE_SOURCE_DIR}/cmake/ARGoSPackaging.cmake)


include(${CMAKE_SOURCE_DIR}/cmake/FindPthreads.cmake)

# Set up ARGoS compilation information
include_directories(${CMAKE_SOURCE_DIR} ${ARGOS_INCLUDE_DIRS})
link_directories(${ARGOS_LIBRARY_DIRS})


add_executable(epuck_ros_interface epuck_ros_interface.h epuck_ros_interface.cpp)
target_link_libraries(epuck_ros_interface
  argos3core_epuck
  argos3plugin_epuck_epuck
  argos3plugin_epuck_genericrobot)

install(FILES epuck_ros_interface.h                       DESTINATION include/epuck_ros_interface)



#ifndef EPUCK_ROS_INTERFACE_H
#define EPUCK_ROS_INTERFACE_H

#include <argos3/core/control_interface/ci_controller.h>
#include <argos3/plugins/robots/e-puck/control_interface/ci_epuck_wheels_actuator.h>
#include <argos3/plugins/robots/e-puck/control_interface/ci_epuck_proximity_sensor.h>
#include <argos3/plugins/robots/e-puck/control_interface/ci_epuck_light_sensor.h>
#include <argos3/plugins/robots/e-puck/control_interface/ci_epuck_ground_sensor.h>
#include <argos3/plugins/robots/e-puck/control_interface/ci_epuck_omnidirectional_camera_sensor.h>
#include <argos3/plugins/robots/e-puck/control_interface/ci_epuck_range_and_bearing_sensor.h>
#include <argos3/plugins/robots/e-puck/control_interface/ci_epuck_range_and_bearing_actuator.h>
#include <argos3/plugins/robots/e-puck/control_interface/ci_epuck_rgb_leds_actuator.h>
#include <argos3/core/utility/configuration/argos_configuration.h>
#include <string>
#include <sstream>
#include <math.h>

//ros related libraries
#include "ros/ros.h"
#include <sensor_msgs/Range.h>
#include <sensor_msgs/LaserScan.h>
#include <geometry_msgs/Twist.h>
#include <std_msgs/ColorRGBA.h>

//epuck dimensions parameters
#define ROBOT_RADIUS 0.035
#define WHEEL_DIAMETER 4       
#define WHEEL_SEPARATION 0.53
#define WHEEL_DISTANCE 0.053  

using namespace argos;


class CEPuckRos : public CCI_Controller {

public:

   CEPuckRos();

   virtual ~CEPuckRos() {}

   virtual void Init(TConfigurationNode& t_node);

   virtual void ControlStep();

   virtual void Reset() {}

   virtual void Destroy() {}

   virtual void initRos(); //must be called in Init
   virtual void rosControlStep();
   virtual void handlerLED(const std_msgs::ColorRGBA::ConstPtr& color_msg);
   virtual void handlerVelocity(const geometry_msgs::Twist::ConstPtr& msg); //handle messages received from steering

private:

   CCI_EPuckWheelsActuator* m_pcWheels;
   CCI_EPuckProximitySensor* m_pcProximity;
   CCI_EPuckRGBLEDsActuator* m_pcLEDs;
   CCI_EPuckGroundSensor* m_pcGround;
   CCI_EPuckLightSensor* m_pcLight;
   CCI_EPuckRangeAndBearingSensor* m_pcRAB;
   CCI_EPuckOmnidirectionalCameraSensor* m_pcCamera;
   
   //proximity ros publisher
   ros::Publisher proximity_pubs[8];
   sensor_msgs::Range proximity_msgs[8];

   //laser scan ros publisher
   ros::Publisher laserPublisher;
   sensor_msgs::LaserScan laserMsg;

   //light ros publisher
   ros::Publisher light_pubs[8];
   sensor_msgs::Range light_msgs[8];

   //ground ros publisher
   ros::Publisher ground_pubs[3];
   sensor_msgs::Range ground_msgs[3];


   ros::Subscriber colorSubscriber;

   ros::Subscriber cmdVelSubscriber;
   Real speedLeft;
   Real speedRight;

   UInt32 id;
   double min_range;
   double max_range;

};

#endif

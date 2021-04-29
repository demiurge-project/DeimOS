#ifndef TEMPLATE_CONTROLLER_H
#define TEMPLATE_CONTROLLER_H

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
#include <math.h>

//epuck dimensions parameters
#define ROBOT_RADIUS 0.035
#define WHEEL_DIAMETER 4       
#define WHEEL_SEPARATION 0.53
#define WHEEL_DISTANCE 0.053  

using namespace argos;


class CEPuck : public CCI_Controller {

public:

   CEPuck();

   virtual ~CEPuck() {}

   virtual void Init(TConfigurationNode& t_node);

   virtual void ControlStep();

   virtual void Reset() {}

   virtual void Destroy() {}

private:

   CCI_EPuckWheelsActuator* m_pcWheels;
   CCI_EPuckProximitySensor* m_pcProximity;
   CCI_EPuckRGBLEDsActuator* m_pcLEDs;
   CCI_EPuckGroundSensor* m_pcGround;
   CCI_EPuckLightSensor* m_pcLight;
   CCI_EPuckRangeAndBearingSensor* m_pcRAB;
   CCI_EPuckOmnidirectionalCameraSensor* m_pcCamera;

   Real speedLeft;
   Real speedRight;

   UInt32 id;
   double min_range;
   double max_range;

};

#endif

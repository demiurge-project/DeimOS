#include "template_controller.h"

using namespace std;

CEPuck::CEPuck() :
  m_pcWheels(NULL),
  m_pcProximity(NULL),
  m_pcGround(NULL),
  m_pcLight(NULL),
  m_pcLEDs(NULL),
  m_pcCamera(NULL),
  id(0),
  speedLeft(2.0f),
  speedRight(2.0f),
  min_range(0.002),
  max_range(0.07){}


void CEPuck::Init(TConfigurationNode& t_node) {
   
  m_pcWheels      = GetActuator<CCI_EPuckWheelsActuator              >("epuck_wheels"                       );
  m_pcProximity   = GetSensor  <CCI_EPuckProximitySensor             >("epuck_proximity"                    );
  m_pcGround      = GetSensor  <CCI_EPuckGroundSensor                >("epuck_ground"                       );
  m_pcLight       = GetSensor  <CCI_EPuckLightSensor                 >("epuck_light"                        );
  m_pcLEDs        = GetActuator<CCI_EPuckRGBLEDsActuator             >("epuck_rgb_leds"                     );
  m_pcCamera      = GetSensor  <CCI_EPuckOmnidirectionalCameraSensor >("epuck_omnidirectional_camera"       );

  GetNodeAttributeOrDefault(t_node, "velocity", speedLeft, speedRight);
  GetNodeAttributeOrDefault(t_node, "id", id, id);
  GetNodeAttributeOrDefault(t_node, "min_range", min_range, min_range);
  GetNodeAttributeOrDefault(t_node, "max_range", max_range, max_range);

}

void CEPuck::ControlStep() 
{
  //do robot stuff
}

REGISTER_CONTROLLER(CEPuck, "template_controller")

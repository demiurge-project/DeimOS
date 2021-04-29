#include "epuck_ros_interface.h"

using namespace std;

CEPuckRos::CEPuckRos() :
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


void CEPuckRos::Init(TConfigurationNode& t_node) {
   
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

  //init ROS
  initRos();
}

void CEPuckRos::initRos(){
  //start ROS
  std::stringstream name;
  name.str("");
  name << GetId();

  if(!ros::isInitialized()){
    char** argv = NULL;
    int argc = 0;
    ros::init(argc, argv, name.str());
  }
  //ros access node
  ros::NodeHandle rosNode;

  //init steering
  cmdVelSubscriber = rosNode.subscribe("cmd_vel", 10, &CEPuckRos::handlerVelocity, this);
  colorSubscriber  = rosNode.subscribe("color", 10, &CEPuckRos::handlerLED, this);
  
  //init proximity and light
  std::stringstream ss;
  for (int i = 0; i < 8; ++i)
  {
    ss.str("");
    ss << name.str() << "/proximity";
    proximity_pubs[i] = rosNode.advertise<sensor_msgs::Range>(ss.str(), 10);
    proximity_msgs[i].radiation_type = sensor_msgs::Range::INFRARED;
    ss.str("");
    ss << name.str() << "/base_prox" << i;
    proximity_msgs[i].header.frame_id = ss.str();
    proximity_msgs[i].field_of_view = 0.26;  
    proximity_msgs[i].min_range = 0;
    proximity_msgs[i].max_range = 1;

    ss.str("");
    ss << name.str() << "/light";
    light_pubs[i] = rosNode.advertise<sensor_msgs::Range>(ss.str(), 10);
    light_msgs[i].radiation_type = sensor_msgs::Range::INFRARED;
    ss.str("");
    ss << name.str() << "/base_light" << i;
    light_msgs[i].header.frame_id = ss.str();  
    light_msgs[i].min_range = 0;     
    light_msgs[i].max_range = 1; 
  }

  //init laser scan
  ss.str("");
  ss << name.str() << "/base_laser";
  laserMsg.header.frame_id = ss.str();
  laserMsg.angle_min = -M_PI;
  laserMsg.angle_max = M_PI;
  laserMsg.angle_increment = M_PI/4.0;
  laserMsg.range_min = min_range+ROBOT_RADIUS; // laser scan situated in the center of the robot
  laserMsg.range_max = max_range+ROBOT_RADIUS; // laser scan situated in the center of the robot
  laserMsg.ranges.resize(8);
  laserMsg.intensities.resize(8);
  ss.str("");
  ss << name.str() << "/scan";
  laserPublisher = rosNode.advertise<sensor_msgs::LaserScan>(ss.str(), 10);

  //init ground
  
  for (int i = 0; i < 3; ++i)
  {
    ss.str("");
    ss << name.str() << "/ground";
    ground_pubs[i] = rosNode.advertise<sensor_msgs::Range>(ss.str(), 10);
    ground_msgs[i].radiation_type = sensor_msgs::Range::INFRARED;
    ss.str("");
    ss << name.str() << "/base_ground" << i;
    ground_msgs[i].header.frame_id = ss.str(); 
    ground_msgs[i].min_range = 0;     
    ground_msgs[i].max_range = 1;  
  }

}

void CEPuckRos::ControlStep() 
{
  if(ros::ok())
  {
    //send all ROS messages
    rosControlStep();
    
    ros::spinOnce();
  }
}

void CEPuckRos::rosControlStep()
{
  //proximity messages
  for(int i=0; i<8; i++) 
  {
    
    if(m_pcProximity->GetReadings()[i].Value > 0) {
        proximity_msgs[i].range = (1-m_pcProximity->GetReadings()[i].Value)*proximity_msgs[i].max_range;  
    } else {
        proximity_msgs[i].range = proximity_msgs[i].max_range;
    }
    if(proximity_msgs[i].range > proximity_msgs[i].max_range) {
        proximity_msgs[i].range = proximity_msgs[i].max_range;
    }
    if(proximity_msgs[i].range < proximity_msgs[i].min_range) {
        proximity_msgs[i].range = proximity_msgs[i].min_range;
    }
    proximity_msgs[i].header.stamp = ros::Time::now();
    proximity_pubs[i].publish(proximity_msgs[i]);

    if(m_pcLight->GetReadings()[i].Value > 0) {
        light_msgs[i].range = (1-m_pcLight->GetReadings()[i].Value)*light_msgs[i].max_range;  
    } else {
        light_msgs[i].range = light_msgs[i].max_range;
    }
    if(light_msgs[i].range > light_msgs[i].max_range) {
        light_msgs[i].range = light_msgs[i].max_range;
    }
    if(light_msgs[i].range < light_msgs[i].min_range) {
        light_msgs[i].range = light_msgs[i].min_range;
    }
    light_msgs[i].field_of_view = m_pcLight->GetReadings()[i].Angle.GetValue();
    light_msgs[i].header.stamp = ros::Time::now();
    light_pubs[i].publish(light_msgs[i]);
  }

  float tempProx;
  tempProx = proximity_msgs[4].range;
  if(tempProx > 0) {   
      laserMsg.ranges[0] = tempProx+ROBOT_RADIUS;  
  } else { 
      laserMsg.ranges[0] = laserMsg.range_max;
  }

  tempProx = proximity_msgs[5].range;
  if(tempProx > 0) {   
      laserMsg.ranges[1] = tempProx+ROBOT_RADIUS; 
  } else { 
      laserMsg.ranges[1] = laserMsg.range_max;
  }
  
  tempProx = proximity_msgs[6].range;
  if(tempProx > 0) {   
      laserMsg.ranges[2] = tempProx+ROBOT_RADIUS; 
  } else { 
      laserMsg.ranges[2] = laserMsg.range_max;
  }
  
  tempProx = proximity_msgs[7].range;
  if(tempProx > 0) {   
      laserMsg.ranges[3] = tempProx+ROBOT_RADIUS; 
  } else { 
      laserMsg.ranges[3] = laserMsg.range_max;
  }        
  
  tempProx = proximity_msgs[0].range;
  if(tempProx > 0) {   
      laserMsg.ranges[4] = tempProx+ROBOT_RADIUS; 
  } else { 
      laserMsg.ranges[4] = laserMsg.range_max;
  }        
  
  tempProx = proximity_msgs[1].range;
  if(tempProx > 0) {   
      laserMsg.ranges[5] = tempProx+ROBOT_RADIUS; 
  } else { 
      laserMsg.ranges[5] = laserMsg.range_max;
  }        
  
  tempProx = proximity_msgs[2].range;
  if(tempProx > 0) {   
      laserMsg.ranges[6] = tempProx+ROBOT_RADIUS; 
  } else { 
      laserMsg.ranges[6] = laserMsg.range_max;
  }        
  
  tempProx = proximity_msgs[3].range;
  if(tempProx > 0) {   
      laserMsg.ranges[7] = tempProx+ROBOT_RADIUS; 
  } else { 
      laserMsg.ranges[7] = laserMsg.range_max;
  }                     
  
  for(int i=0; i<8; i++) {
      if(laserMsg.ranges[i] > laserMsg.range_max) {
          laserMsg.ranges[i] = laserMsg.range_max;
      }
      if(laserMsg.ranges[i] < laserMsg.range_min) {
          laserMsg.ranges[i] = laserMsg.range_min;
      }
  }
  
  laserMsg.header.stamp = ros::Time::now();
  laserPublisher.publish(laserMsg);
  

  for(int i=0; i<3; i++) 
  { 
    if(m_pcGround->GetReadings()[i] > 0) {
        ground_msgs[i].range = (m_pcGround->GetReadings()[i]);  
    } else {
        ground_msgs[i].range = ground_msgs[i].max_range;
    }
    
    if(ground_msgs[i].range > ground_msgs[i].max_range) {
        ground_msgs[i].range = ground_msgs[i].max_range;
    }
    if(ground_msgs[i].range < ground_msgs[i].min_range) {
        ground_msgs[i].range = ground_msgs[i].min_range;
    }
    ground_msgs[i].header.stamp = ros::Time::now();
    ground_pubs[i].publish(ground_msgs[i]);
  }


}

void CEPuckRos::handlerVelocity(const geometry_msgs::Twist::ConstPtr& msg) 
{
    // Controls the velocity of each wheel based on linear and angular velocities.
    float linear = msg->linear.x/10.0f;    //values from teleop too high -> /10
    float angular = msg->angular.z/10.0f;

    float wl = (linear - ((WHEEL_SEPARATION / 2.0f) * angular)) / WHEEL_DIAMETER;
    float wr = (linear + ((WHEEL_SEPARATION / 2.0f) * angular)) / WHEEL_DIAMETER;

    // At input 1000, angular velocity is 1 cycle / s or  2*pi/s 
    //+ factor *3 comes from empirical tests + /10 to reduce the speed of the epuck and improve map quality
    speedLeft = wl * 3000.0f/10;
    speedRight = wr * 3000.0f/10;
    
    m_pcWheels->SetLinearVelocity(speedLeft, speedRight);

    CColor* color = new CColor(((UInt8)floor(linear)), ((UInt8)floor(angular)), ((UInt8)125));
    m_pcLEDs->SetColors(*color);

}

void CEPuckRos::handlerLED(const std_msgs::ColorRGBA::ConstPtr& color_msg) 
{
    CColor* color = new CColor(((UInt8)color_msg->r), ((UInt8)color_msg->g), ((UInt8)color_msg->b));
    m_pcLEDs->SetColors(*color);
}

REGISTER_CONTROLLER(CEPuckRos, "epuck_ros_interface_controller")

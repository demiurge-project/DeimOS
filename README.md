DeimOS README
===================
:Author: Miquel Kegeleirs
:Email:  miquel.kegeleirs@ulb.be
:Date:   March 15th, 2021

What is DeimOS?
--------------------

DeimOS is an open-source Linux-based operating system for the G-puck (i.e., the E-puck robot extended with the Linux extension board Gumstix Overo COM, the range-and-bearing board, and the Omnivision module) that allows using ROS Melodic in swarm robotics experiments. DeimOS is developed  under the framework of the Yocto Project (https://www.yoctoproject.org/) and is configured to operate with the structural ROS software packages and the libraries of the swarm robotics simulator ARGoS3. By including ROS in the G-puck, we aim to provide inter-operability between G-pucks and e-puck robots using other extensions such as the Pi-puck extension board or \textit{ad hoc} implementations like the X-puck using Hardkernel Odroid XU4. 

DeimOS is a ready-to use OS that buils upon the Warrior branch of the Gumstix Yocto Manifest (https://github.com/gumstix/yocto-manifest), the Warrior branch of the meta-ros Yocto layer (https://github.com/ros/meta-ros), and a particular implementation of the rtl8192cu Wi-Fi driver (https://github.com/lwfinger/rtl8192cu), and provides proper configuration for the G-puck robot as well as support for ARGoS3 and ressources to build ARGoS3 controllers using (or not) ROS.

DeimOS is released under the terms of the GPLv2 license.

Downloading DeimOS
-----------------------

You can download the development sources through git:

 $ git clone https://github.com/demiurge-project/DeimOS DeimOS

Compiling DeimOS
---------------------

**1. Initialize the Yocto Project Build Environment**

 $ export TEMPLATECONF=meta-gumstix-extras/conf 
 $ source ./poky/oe-init-build-env

**2. Build an image**

 $ bitbake gumstix-console-image
 
 This might take some time.

**3. Create a bootable micro SD card**

You can follow the instructions given here: [Create Bootable MicroSD Card](https://www.gumstix.com/support/getting-started/create-bootable-microsd-card/ "Create Card")

Additional setup
---------------------

In order to connect to your network, the /etc/network/interfaces and /etc/wpa-supplicant/wpa-supplicant-wlan0.conf files need to be properly configured.
The G-puck should then connect automatically at boot.
In case the G-puck does not connect, check your configuration and try:

 $ /etc/init.d/rc.local restart

DeimOS enables ssh by default, once the network connection is established you can connect directly to the G-puck using ssh.

Templates
---------------------

A recipe template for a custom ARGoS controller for the G-puck can be found in poky/meta-epuck/recipes-controller

A recipe template for an additional ROS package can be found in poky/meta-epuck

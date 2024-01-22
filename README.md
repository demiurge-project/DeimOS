DeimOS README
===================
:Author: Miquel Kegeleirs
:Email:  miquel.kegeleirs@ulb.be
:Date:   March 15th, 2021

What is DeimOS?
--------------------

DeimOS is an open-source Linux-based operating system for the G-puck (i.e., the E-puck robot extended with the Linux extension board Gumstix Overo COM, the range-and-bearing board, and the Omnivision module) that allows using ROS Melodic in swarm robotics experiments. DeimOS is developed  under the framework of the Yocto Project (https://www.yoctoproject.org/) and is configured to operate with the structural ROS software packages and the libraries of the swarm robotics simulator ARGoS3. By including ROS in the G-puck, we aim to provide inter-operability between G-pucks and e-puck robots using other extensions such as the Pi-puck extension board or \textit{ad hoc} implementations like the X-puck using Hardkernel Odroid XU4. 

DeimOS is a ready-to use OS that buils upon the Warrior branch of the Gumstix Yocto Manifest (https://github.com/gumstix/yocto-manifest), the Warrior branch of the meta-ros Yocto layer (https://github.com/ros/meta-ros), and a particular implementation of the rtl8192cu Wi-Fi driver (https://github.com/lwfinger/rtl8192cu), and provides proper configuration for the G-puck robot as well as support for ARGoS3 and ressources to build ARGoS3 controllers using (or not) ROS.

DeimOS is released under the terms of the GPLv3 license.

Downloading DeimOS
-----------------------

You can download the development sources through git:

 $ git clone https://github.com/demiurge-project/DeimOS DeimOS

Preparing the environment
---------------------

On Debian, you can install all of the necessary requirements
with the following command:

 $ sudo apt-get install chrpath diffstat gawk texinfo 

Compiling DeimOS
---------------------

**1. Initialize the Yocto Project Build Environment**

 $ cd DeimOS
 $ export TEMPLATECONF=meta-gumstix-extras/conf 
 $ source ./poky/oe-init-build-env

**2. Build an image**

 $ bitbake gumstix-console-image
 
 This might take some time depending on your system.
 
 Some common issues:
 
 * Some packages (e.g., pcl) require more computing power to build than the average, and the compilation process might freeze and ultimately fail on older systems.
 Compiling the package alone (e.g., bitbake pcl) before restarting the full build (bitbake gumstix-console-image) solves the issue in some cases, but if the process keeps freezing, you might need to consider a more recent system for the compilation.
 
 * On the contrary, on very powerful systems, the compilation process might fail because of fetching errors from github. This is due to the system processing to many fetching in parallel.
 Restarting the build process (bitbake gumstix-console-image) solves the issue.
 
 * A QA related error interrupts occasionaly the build of argos3 and argos3-epuck. Again, restarting the build process (bitbake gumstix-console-image) solves the issue.
 

**3. Create a bootable micro SD card**

You can follow the instructions given here: [Create Bootable MicroSD Card](https://www.gumstix.com/support/getting-started/create-bootable-microsd-card/ "Create Card").

The required files can be found in build/tmp/deploy/images/overo/ after succesful build.
Prebuilt image files can also be found on [Zenodo](https://doi.org/10.5281/zenodo.10551351 "Prebuilt files").

Alternatively, it is also possible to use wic to create a .direct file that can then be flashed on the micro SD card using bmap-tools.

After the build process is complete, in the build folder, create the image file (a prebuilt file is also available on [Zenodo](https://doi.org/10.5281/zenodo.10551351 "Prebuilt files")):

 $ wic create sdimage-gumstix -e gumstix-console-image
 
Then, use bmap-tools to flash your device:

 $ sudo apt-get install bmap-tools

 $ bmaptool create sdimage-gumstix-mmcblk0.direct > image.bmap
 
 $ sudo bmaptool copy --bmap image.bmap sdimage-gumstix-mmcblk0.direct /dev/DEVICE_NAME
 
 where DEVICE_NAME is the name of the  microSD card (for example : mmcblk0)

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

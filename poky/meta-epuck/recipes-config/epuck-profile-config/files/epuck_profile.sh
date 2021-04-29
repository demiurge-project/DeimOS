#!/bin/sh
source /opt/ros/melodic/setup.bash
rm /dev/ttyS0
ln -s /dev/ttyO0 /dev/ttyS0
ln -s /dev/i2c-2 /dev/i2c-3
rm /dev/video1
ln -s /dev/video7 /dev/video1

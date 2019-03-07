# CENG 355 Senz Car

The purpose of this project is to build a telemetry system for a Baja racing vehicle. There are three sensors involved in this project which include a PCF8591, BME280 and AMG8833. This is a collaborative project that involves up to 3 members who each contributed to at least one sensor and the overall dynamics of this project. WE put together a [Budget Plan](https://github.com/KogulB/CENG355Project/blob/master/documentation/Budget.xlsx) and a project [schedule](https://github.com/KogulB/CENG355Project/blob/master/documentation/Schedule.mpp) to help us utilize a project in a successful manner.
![PiEnclousre](https://raw.githubusercontent.com/KogulB/KogulBCENG317Project/master/Images/PiEnclosure.jpg)

## Introduction - System Diagram

![SystemDiagram1](https://raw.githubusercontent.com/KogulB/CENG355Project/master/Hardware/SystemDiagram1.PNG)
![SystemDiagram2](https://raw.githubusercontent.com/KogulB/CENG355Project/master/Hardware/SystemDiagram2.PNG)
![SystemDiagram3](https://raw.githubusercontent.com/KogulB/CENG355Project/master/documentation/AMG8833%20System%20Diagram.png)
## 2 Budget Plan/Bill

The main components of the project were an Rpi 3 Kit which included a power cable and a default case. Memory card would have to be purchased separately but for our case we had one already. More purchases made were the sensors and devices needed to run everything: PCF8591, BME280, AMG8833, Ethernet wire and adapter. HDMI cable and breadboard might need to be purchased as well but in our case we had previously owned on already. There are however some extra parts purchased for testing but were never user used. The link to my budget plan can be found [here](https://github.com/KogulB/CENG355Project/blob/master/documentation/Budget.xlsx).

## 3 Time Commitment 

The time it took to do the research portion of our project took about two hours per sensor because  we each did our own research on a different sensor to find all the components needed to get the ICs up and running. So in total research took about 6 hours(2x 3 sensors).

The materials could take anywhere from 2-5 days to ship if they are ordered from amazon and depending on the shipping method used. The sensors were however purchased from different websites (Links in the Budget) and took about 1 week to arrive.

After getting all the components listed in the budget plan to test connect each sensor to the Rpi using a breadboard. Make sure to test each sensor separately as to not short circuit the sensors. This should take roughly 2 hours. 

After figuring out the connection our partner spent about three hours creating the connection on Fritizing: Our partner spent another 3 hours designing and moving around the PCB diagram by adding Vias to it and making sure the connections didn’t overlap. At the end this is how the PCBs should look like.
AMG8833 [diagram](https://raw.githubusercontent.com/KogulB/CENG355Project/master/Hardware/Pictures/AMG8833%20PCB%20Fritzing%20Screenshot.PNG).
[PCF8591](https://raw.githubusercontent.com/KogulB/CENG355Project/master/Hardware/Pictures/PCF8591%20PCB%20Fritzing%20Screenshot.PNG)
[BME280](https://raw.githubusercontent.com/KogulB/CENG355Project/master/Hardware/Pictures/BME280%20PCB%20Fritzing%20Screenshot.PNG)
[RPiConnecection](https://raw.githubusercontent.com/KogulB/CENG355Project/master/Hardware/Pictures/Main%20Board%20PCB%20Fritzing%20Screenshot.PNG)

Creating the code for testing is not hard at all. Pull code from StudentSenseHat Git. Since PCF8591 was used in that project Testing took about 30 minutes. For the BME280 we found the code online for testing the sensor from the following [website](https://github.com/adafruit/Adafruit_CircuitPython_BME280/blob/master/examples/bme280_simpletest.py). The AMG8833 was also online and can be found on this [link](https://github.com/adafruit/Adafruit_CircuitPython_AMG88xx/blob/master/examples/amg88xx_simpletest.py). 

### Total Commitment: 14 hours(excluding shipping)

## 4 PCB/Soldering

Cut off the pins located on the sensors and solder new header pins on both sides so connection to a breadboard becomes possible. 

solder new header pins on to PCB so connections to Rpi and sensor is possible. 

![SolderedParts](https://raw.githubusercontent.com/KogulB/CENG355Project/master/Hardware/Pictures/All%20Hardware.jpg)

## 5 Mechanical Assembly

To be added later as all three sensors have not been connected together on one board.

## 6 Power Up

To be added when all parts are working on one enclosure

## 7 Production Testing

After powering the Rpi make sure you test if the address the 8591 is connected to is actually correct

 1. Connnect Pi to a display using HDMI wire(or HDMI adapter)
 2. Turn on the Pi (wait for it to boot up)
 3. Open a terminal window
 4. Run the command sudo raspi config
 5. Enable I2C Address
 6. Exit out
 7. Run sudo reboot
 8. After reboot open another terminal window
 9. Run the command  sudo I2Cdetect -y 1
 10. It should show that the address that are connected is 0x48 , 0x69 and 0x77

[Example](https://raw.githubusercontent.com/KogulB/KogulBCENG317Project/master/Images/Assigned.PNG)

## 8 Unit Testing

To be added when testing is done rougly in about two weeks

## 9  Mass Production

If there was to be mass production of this project what we as a group would personally change is not soldering off the pins of the stock sensor. I feel that by extending that case higher and my sensor facing up on the PCB it saves less time and effort for mass production. Similar to how the PCF8591 was setup on StudentSenseHat. I would also use a machine that solders the wires on to the PCB to save more time from me doing them myself.

![studentsensehat](https://raw.githubusercontent.com/six0four/StudentSenseHat/master/images/39.jpg)

Source(https://raw.githubusercontent.com/six0four/StudentSenseHat/master/images/39.jpg)


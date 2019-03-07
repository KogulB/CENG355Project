# CENG 317 PCF8591

The purpose of this project was to get a better understanding of the sensor I chose which was a PCF8591. I will use my sensor with my partners next semester as a collaboration project. I had to do some research on my sensor to learn about how much it would cost and what I needed to make it work with the Raspberry Pi. I put together a [Budget Plan](https://github.com/KogulB/KogulBCENG317Project/blob/master/documentation/Budget.xlsx) and a project Schedule to help me utilize my project in a successful manner.
![PiEnclousre](https://raw.githubusercontent.com/KogulB/KogulBCENG317Project/master/Images/PiEnclosure.jpg)

## Introduction - System Diagram

![SystemDiagram](https://raw.githubusercontent.com/KogulB/KogulBCENG317Project/master/Images/SystemDiagram.PNG)

## 2 Budget Plan/Bill

The main components of the project was an Rpi 3 Kit which included a power cable and a default case. Memory card would have to be purchased separately but for myself I had my own already. More purchases made were the PCF8591, Jumper cables, Ethernet wire and adapter. HDMI cable and breadboard might need to be purchased as well but I previously own on already. There are however some extra parts purchased for testing but were never user used. The link to my budget plan can be found [here](https://github.com/KogulB/KogulBCENG317Project/blob/master/documentation/Budget.xlsx).

## 3 Time Commitment 

The time it took to do the research portion of my project took about two hours to find all the components needed to get my IC up and running.

The materials could take anywhere from 2-5 days to ship if they are ordered from amazon and depending on the shipping method used. The PCF 8591 was however bought at the local Creatron store. 

After getting all the components hooking up the Rpi 3 to the sensor took around 3 hours. Toughest part of putting it together was figuring out where each part goes and how to connect it using a breadboard. I used a diagram from google to find out where each connector goes on the pcf8591 and a diagram of Rpi 3 pin layout to figure where each connection is established. 

After figuring out the connection I spent about two hours creating the connection [diagram](https://raw.githubusercontent.com/KogulB/KogulBCENG317Project/master/PCF8591PCBLayout/BreadBoardLayout.PNG). This diagram gave me a version of a PCB. I spent another 5 hours designing and moving around the PCB diagram by adding Vias to it and making sure the connections didn’t overlap. At the end this is how the PCB should look like.

![PCB](https://raw.githubusercontent.com/KogulB/KogulBCENG317Project/master/PCF8591PCBLayout/PCF8591PCB.PNG)

Creating the code for testing is not hard at all. Pull code from StudentSenseHat Git. Since PCF8591 was used in that project Testing took about 30 minutes.

### Total Commitment: 14 hours(excluding shipping)

## 4 PCB/Soldering

Cut off the pins located on the PCF8591 and solder new header pins on both sides so connection to a breadboard becomes possible. 

solder new header pins on to PCB so connections to Rpi and sensor is possible. 

![SolderedParts](https://raw.githubusercontent.com/KogulB/KogulBCENG317Project/master/PCF8591PCBLayout/PCBBoardSoldered.jpg)

## 5 Mechanical Assembly

Raspberry Pi 3: Since the Rpi 3 kit came with a power supply and case it connection easier

PCF8591: Connect the PCF8591 to the Pins specified in the Diagram below as well as the proper connection to the Rpi.

![diagram](https://raw.githubusercontent.com/KogulB/KogulBCENG317Project/master/PCF8591PCBLayout/PCF8591Schematic.PNG)

Source: hackster.imgix.net
![here](https://hackster.imgix.net/uploads/attachments/217647/oDRh4lpYwoZHHrJiQR64.png?auto=compress%2Cformat&w=740&h=555&fit=max) 

1. VCC connects to Pin 2(5V)
2. GND connects to Pin 6(GND)
3. SDA connects to Pin 3(SDA1 I2C)
4. SCL connects to Pin 5 (SCL1 I2C)

Breadboard: When connecting to the breadboard I used ribbon cables provided with the board as well as jumper cables from my parts kit to connect the Rpi.

The diagram should look something like this if using a breadboard 

![breadboard](https://raw.githubusercontent.com/KogulB/KogulBCENG317Project/master/Images/piSetupjpeg.jpeg)

## 6 Power Up

After making the connections a red indicator light on the Sensor should light up. SUGESTION: connect the Rpi 3 GND pin first before connecting the power pin. As Indicated above by the breadboard diagram.

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
 10. It should show that the address is 0x48

[Example](https://raw.githubusercontent.com/KogulB/KogulBCENG317Project/master/Images/Assigned.PNG)

## 8 Unit Testing

 1. Run the command sudo git clone https://github.com/six0four/StudentSenseHat.git
 2. This should clone the Student Sense Hat git to your root folder
 3. Run cd /StudentSenseHat/firmware
 4. Then run gcc -Wall -o traffic2B traffic2B.c -lwiringPi to compile the code
 5. Then to run the code sudo ./traffic2B

This should cause a green [light](https://raw.githubusercontent.com/KogulB/KogulBCENG317Project/master/Images/LightSensor.jpg) to emit from the Pi. And the data being read should change the luminosity based on the light sensor on the PCF8591.

To test try covering the sensor with your hand to see what happens to the luminosity value(Light). 

## 9  Mass Production

If there was to be mass production of my project what I would personally change is not soldering off the pins of the stock sensor. I feel that by extending that case higher and my sensor facing up on the PCB it saves less time and effort for mass production. Similar to how the PCF8591 was setup on StudentSenseHat. I would also use a machine that solders the wires on to the PCB to save more time from me doing them myself.

![studentsensehat](https://raw.githubusercontent.com/six0four/StudentSenseHat/master/images/39.jpg)

Source(https://raw.githubusercontent.com/six0four/StudentSenseHat/master/images/39.jpg)

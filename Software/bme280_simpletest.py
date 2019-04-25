import time

import board
import busio
import adafruit_bme280
import json
#import urllib2
from firebase import firebase
myFirebase = firebase.FirebaseApplication('https://senzroomapp.firebaseio.com/')
# Create library object using our Bus I2C port
i2c = busio.I2C(board.SCL, board.SDA)
bme280 = adafruit_bme280.Adafruit_BME280_I2C(i2c)

# OR create library object using our Bus SPI port
#spi = busio.SPI(board.SCK, board.MOSI, board.MISO)
#bme_cs = digitalio.DigitalInOut(board.D10)
#bme280 = adafruit_bme280.Adafruit_BME280_SPI(spi, bme_cs)

# change this to match the location's pressure (hPa) at sea level
bme280.sea_level_pressure = 1013.25

for i in range(5):
    print("\nAir Pressure: %0.1f hPa" % bme280.pressure)
    myFirebase = firebase.FirebaseApplication('https://senzcar2.firebaseio.com/')
    myFirebase.put("/Pressure",'pressure' ,str(bme280.pressure))
    time.sleep(0.05)

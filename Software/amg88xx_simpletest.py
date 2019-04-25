import time
import busio
import board
import adafruit_amg88xx
import json
from firebase import firebase

i2c = busio.I2C(board.SCL, board.SDA)
amg = adafruit_amg88xx.AMG88XX(i2c)
tot = 0

#Runs 3 times, first 2 are to make sure sensor is initialized
for i in range(3):
    for row in amg.pixels: #For each row in 8x8 grid
        
        #Below line used if wanting to display entire grid to console
        #print(['{0:.1f}'.format(temp) for temp in row])
        
        for temp in row: #For each value in 8 value row
            # Add all values in each line together, pad to 1 decimal place
            tot = tot + float(temp)
        
    print("\n")
    #Divide by number of values to get average temp detected
    avg = '{0:.1f}'.format(tot/64)
    #Reset total to 0 for next run
    tot=0
    #Print avg for debugging
    print(avg)

    #Sleep for 0.1 second
    time.sleep(0.1)
    
#Send data to firebase
myFirebase = firebase.FirebaseApplication('https://senzcar2.firebaseio.com/')
postdata = myFirebase.put("Thermal",'Thermal', avg)

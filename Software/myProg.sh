#!/bin/bash
x="2"
y="0"
while [ 1 ]
do

	python PCF8591.py &&  python3 bme280_simpletest.py && python3 amg88xx_simpletest.py 

	#x=$(("$x" * "$y"))
	sleep 5	
	
done


# Overview  
This is a small program with the goal of simulating a MasterLock combination lock.  

## Usage  
From Un*x terminal, simplest way is `java -jar lock.jar`  
You can supply three ints as parameters for the lock `java -jar lock.jar 9 32 8`  
You may also build it from source by navigating the MasterLockBuild gradle directory

## Assumptions  
* In the combo, X is first, Y second, Z third  
* One cannot turn right or left by 0 or a negative number  
* To open a closed lock:  
     1. Provide any amount of turning until landing on X,  
     2. Make one full rotation left, and continue until reaching Y,  
     3. Turn right until reaching Z the first time  
* One CAN have equivalent X, Y, and Z  

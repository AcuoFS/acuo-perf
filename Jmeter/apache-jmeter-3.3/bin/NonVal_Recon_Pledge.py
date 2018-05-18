import requests
import sys
import time
import csv
import os
import configparser
import requests

#Clean History
#--------------------------------------------------------------------------------------------------------
print ("Delete history Calls.....")
r=requests.get('https://qa.acuo.com/valuation/acuo/api/import/load/client/Palo?file=deleteCalls')
r=requests.get('https://qa.acuo.com/valuation/acuo/api/import/load/client/Reuters?file=deleteCalls')
r=requests.get('https://qa.acuo.com/valuation/acuo/api/import/load/client/Acuo?file=deleteCalls')
#--------------------------------------------------------------------------------------------------------

# Short seconds delay for completed history clean
time.sleep(10)
print ("Completed")

#Read Config File
#--------------------------------------------------------------------------------------------------------
config = configparser.RawConfigParser()
config.read('test.cfg')
argv1=config.getint('config','threads')
argv2=config.getint('config','rampup')
argv3=config.getint('config','loop')

thread=int(argv1)
rampup=str(argv2)
loop=str(argv3)
#--------------------------------------------------------------------------------------------------------

print ("Start Jmeter Test")
print ("-----------------------")

#Run Jmeter 
#--------------------------------------------------------------------------------------------------------
if sys.platform == "win32":
    os.system("Jmeter -n -t Val_Recon_Pledge.jmx -l Val_Recon_Pledge_Result.csv -J user.thread=%s -J user.rampup=%s -J user.loop=%s" %(thread,rampup,loop))
elif sys.platform == "win64":
    os.system("Jmeter -n -t Val_Recon_Pledge.jmx -l Val_Recon_Pledge_Result.csv -J user.thread=%s -J user.rampup=%s -J user.loop=%s" %(thread,rampup,loop))
else:
    os.system("./jmeter -n -t Val_Recon_Pledge.jmx -l Val_Recon_Pledge_Result.csv -J user.thread=%s -J user.rampup=%s -J user.loop=%s" %(thread,rampup,loop))
#--------------------------------------------------------------------------------------------------------
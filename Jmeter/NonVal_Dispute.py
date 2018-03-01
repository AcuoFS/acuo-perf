import requests
import time
import csv
import os
import ConfigParser
import requests

#Clean History
#--------------------------------------------------------------------------------------------------------
print "Delete history Calls....."
r=requests.get('https://uat.acuo.com/valuation/acuo/api/import/load/client/Palo?file=deleteCalls')
r=requests.get('https://uat.acuo.com/valuation/acuo/api/import/load/client/Reuters?file=deleteCalls')
r=requests.get('https://uat.acuo.com/valuation/acuo/api/import/load/client/Acuo?file=deleteCalls')
#--------------------------------------------------------------------------------------------------------

# Short seconds delay for completed history clean
time.sleep(2)
print "Completed"

#Read Config File
#--------------------------------------------------------------------------------------------------------
config = ConfigParser.RawConfigParser()
config.read('test.cfg')
argv1=config.getint('config','threads')
argv2=config.getint('config','rampup')
argv3=config.getint('config','loop')

thread=int(argv1)
rampup=str(argv2)
loop=str(argv3)
#--------------------------------------------------------------------------------------------------------

print "Start Jmeter Test"
print "-----------------------"

#Run Jmeter 
#--------------------------------------------------------------------------------------------------------
os.system("Jmeter -n -t NonVal_Dispute.jmx -l NonVal_Dispute.csv -J user.thread=%s -J user.rampup=%s -J user.loop=%s" %(thread,rampup,loop))
#--------------------------------------------------------------------------------------------------------
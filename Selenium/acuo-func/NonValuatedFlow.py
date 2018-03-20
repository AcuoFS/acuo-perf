import openpyxl
import sys
import time
import os
import getpass
from datetime import datetime, timedelta
from os.path import expanduser

print("--------------------------------------------------------------------------------")
print("This is a python script to run maven project")
print("--------------------------------------------------------------------------------")


cwd = os.getcwd()
print (cwd)
test_path = cwd + '\\src\\test\\resources\\features\\NonValuatedFlow.feature'

# ----------------Write property file for maven project---------------
property_path = cwd + "\\src\\test\\resources\\my.properties"

if sys.argv[1] == "uat":
	content="https://uat.acuo.com"
elif sys.argv[1] == "dev":
	content = "https://dev.acuo.com"
elif sys.argv[1] == "prod":
	content = "https://prod.acuo.com"

with open(property_path, 'w') as properties:
    properties.write("url="+content+'\n')
# -----------------------------------------------------------------

os.system('mvn test -Dcucumber.options=\"' + test_path +'\"')
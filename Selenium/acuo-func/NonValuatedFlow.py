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
print(sys.platform)

print (cwd)
# ---------------------Define different naming convention for linux and windows path------------------------
if sys.platform == "win32":
	test_path = cwd + '\\src\\test\\resources\\features\\NonValuatedFlow.feature'
	property_path = cwd + "\\src\\test\\resources\\my.properties"
elif sys.platform == "win64":
	test_path = cwd + '\\src\\test\\resources\\features\\NonValuatedFlow.feature'
	property_path = cwd + "\\src\\test\\resources\\my.properties"
else:
	test_path = cwd + '/src/test/resources/features/NonValuatedFlow.feature'
	property_path = cwd + "/src/test/resources/my.properties"

# ----------------Write property file for maven project---------------

if sys.argv[1] == "uat":
	content="https://uat.acuo.com"
elif sys.argv[1] == "dev":
	content = "https://dev.acuo.com"
elif sys.argv[1] == "prod":
	content = "https://prod.acuo.com"
elif sys.argv[1] == "qa":
	content = "https://qa.acuo.com"

with open(property_path, 'w') as properties:
    properties.write("url="+content+'\n')
# -----------------------------------------------------------------

os.system('mvn test -Dcucumber.options=\"' + test_path +'\"')
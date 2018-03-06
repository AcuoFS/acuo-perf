import openpyxl
import time
import os
import getpass
from datetime import datetime, timedelta
from os.path import expanduser

print("--------------------------------------------------------------------------------")
print("This is a python script to run maven project")
print("--------------------------------------------------------------------------------")


cwd = os.getcwd()
print cwd
test_path = cwd + '\\src\\test\\resources\\features\\NonValuatedFlow.feature'

os.system('mvn test -Dcucumber.options=\"' + test_path +'\"')
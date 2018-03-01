import openpyxl
import csv
import os
import getpass
from datetime import datetime, timedelta
import ConfigParser
import requests

#clean history
#------------------------------------------------------------------------------------------------------
print "Delete history Calls....."
r=requests.get('https://uat.acuo.com/valuation/acuo/api/import/load/client/Palo?file=deleteCalls')
r=requests.get('https://uat.acuo.com/valuation/acuo/api/import/load/client/Reuters?file=deleteCalls')
r=requests.get('https://uat.acuo.com/valuation/acuo/api/import/load/client/Acuo?file=deleteCalls')

#Read Config File
#------------------------------------------------------------------------------------------------------
config = ConfigParser.RawConfigParser()
config.read('test.cfg')
argv1=config.getint('config','threads')
argv2=config.getint('config','rampup')
argv3=config.getint('config','loop')

thread=int(argv1)
rampup=str(argv2)
loop=str(argv3)

#Read and generate Valuated File
#------------------------------------------------------------------------------------------------------
with open('files.csv', 'rb') as csvfile:
    reader = csv.reader(csvfile)
    for item in reader:
        print item

# Define Today()-1     
d = datetime.today().date() - timedelta(days=1)
d = d.strftime("%Y/%m/%d")

#Define working Directory   
cwd = os.getcwd()
file_path = cwd + '\\'

print ("Value Date will be " + d)
print ("Working Directory is " + file_path)


itemNr = len(item)
num = 0
while num < itemNr:
    filename=item[num]
    oriFile = filename+'.xlsx'
    valueFile = filename + '_yesterday.xlsx'
    
    print ("file " + str(num+1) + " is " + oriFile)
    print ("Valuated File will be named as " + valueFile)
    #     --------------------------------------------------------------------

    
    wb = openpyxl.load_workbook(file_path + oriFile)
    sheet_one = wb.get_sheet_by_name('IRS-Cleared')
    sheet_two = wb.get_sheet_by_name('FRA-Cleared')
    sheet_three = wb.get_sheet_by_name('OIS-Cleared')
    sheet_four = wb.get_sheet_by_name('IRS-Bilateral')
    sheet_five = wb.get_sheet_by_name('FXSwap-Bilateral')
    sheet_six = wb.get_sheet_by_name('ZCS-Cleared')


    cell_range_one = sheet_one['B2':'B999'] #Selecting the slice of interest
    cell_range_two = sheet_two['B2':'B999']
    cell_range_three = sheet_three['B2':'B999']
    cell_range_four = sheet_four['B2':'B999']
    cell_range_five = sheet_five['B2':'B999']
    cell_range_six = sheet_six['B2':'B999']

    count = 2
    for row in cell_range_one: # This is iterating through rows
        rowNr="A" + str(count)
        for cell in row: # This iterates through the columns(cells) in that row
            value = cell.value
            if cell.value != None:
                sheet_one[rowNr]= d
                count = count + 1
            
    count = 2
    for row in cell_range_two: # This is iterating through rows
        rowNr="A" + str(count)
        for cell in row: # This iterates through the columns(cells) in that row
            value = cell.value
            if cell.value != None:
                sheet_two[rowNr]= d
                count = count + 1
            
    count = 2
    for row in cell_range_three: # This is iterating through rows
        rowNr="A" + str(count)
        for cell in row: # This iterates through the columns(cells) in that row
            value = cell.value
            if cell.value != None:
                sheet_three[rowNr]= d
                count = count + 1

    count = 2
    for row in cell_range_four: # This is iterating through rows
        rowNr="A" + str(count)
        for cell in row: # This iterates through the columns(cells) in that row
            value = cell.value
            if cell.value != None:
                sheet_four[rowNr]= d
                count = count + 1
            
    count = 2
    for row in cell_range_five: # This is iterating through rows
        rowNr="A" + str(count)
        for cell in row: # This iterates through the columns(cells) in that row
            value = cell.value
            if cell.value != None:
                sheet_five[rowNr]= d
                count = count + 1

    count = 2
    for row in cell_range_six: # This is iterating through rows
        rowNr="A" + str(count)
        for cell in row: # This iterates through the columns(cells) in that row
            value = cell.value
            if cell.value != None:
                sheet_six[rowNr]= d
                count = count + 1


    wb.save(os.path.join(file_path, valueFile))
    wb.close()
    num = num + 1
#------------------------------------------------------------------------------------------------------

#Start Jmeter Test
#------------------------------------------------------------------------------------------------------
print "Start Jmeter Test"
print "-----------------------"
os.system("Jmeter -n -t Val_Dispute.jmx -l Val_Dispute.csv -J user.thread=%s -J user.rampup=%s -J user.loop=%s" %(thread,rampup,loop))
    
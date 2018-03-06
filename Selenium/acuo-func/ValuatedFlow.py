import openpyxl
import csv
import sys
import time
import os
import getpass
from datetime import datetime, timedelta
from os.path import expanduser

print("--------------------------------------------------------------------------------")
print("This is a python script to write date to xlsx file and run maven project")
print("--------------------------------------------------------------------------------")

with open('files.csv', 'rb') as csvfile:
    reader = csv.reader(csvfile)
    for item in reader:
        print item

start_time=time.time()
d = datetime.today().date() - timedelta(days=1)
d = d.strftime("%Y/%m/%d")

print ("Date generated = " + d)

cwd = os.getcwd()
print cwd
test_path = cwd + '\\src\\test\\resources\\features\\ValuatedFlow.feature'
file_path = cwd + '\\attachment\\'

print("Test Path : " + test_path)
print("Current Working Directory : " + cwd)

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

end_time = time.time()
total_time = end_time - start_time
print("Total time taken for xlsx generation(seconds): " + str(total_time))


os.system('mvn test -Dcucumber.options=\"' + test_path +'\"')
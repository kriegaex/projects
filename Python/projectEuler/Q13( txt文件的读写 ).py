import time
time_start = time.time()

chaozy =  open('Q13.txt', "r")
array = []
for line in chaozy:
    array.append(line)

# Convert the array into an array of integers
newArray = []
for i in range(len(array)):
#for i in array:
    newArray.append(int(array[i]))
    print(i)

# Sum up the array and print the first 10 numbers of the sum as a string
arraySum = sum(newArray)
print (str(arraySum)[:10])
chaozy.close()

time_end = time.time()
print(time_end - time_start)

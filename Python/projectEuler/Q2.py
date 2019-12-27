i = 1
x = 2
sum = 0
while i < 4000000:
    a = i + x
    if (i % 2 == 0):
        sum = sum + i
    i = x
    x = a
print (sum)

total = 1
for i in range(1, 101):
    total *= i
print(total)
digit = 0
string = str(total)
for i in range(len(string)):
    digit += int(string[i])
print(digit)






from functools import reduce
print(reduce(lambda x, y: x + y, [int(i) for i in str(reduce(lambda x, y: x * y, range(1, 100)))]))

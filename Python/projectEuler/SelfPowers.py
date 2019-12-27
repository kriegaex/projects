import time
a = time.time()
sum1 = 0
for i in range(1,1001):
    sum1 += i ** i
string = str(sum1)
print(string[-10:])


b = time.time()

print(str(sum([i**i for i in range(1,1001)]))[-10:])

c = time.time()
print(b - a)
print(c - b)
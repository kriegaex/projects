import time

time_start = time.time()
def isprime(a):
    for i in range(2, round(a**0.5)+1):
        if a % i == 0:
            return False
    return True
result1, result2, num = [], [], 1

while num < (2000000**0.5 + 1):
    num = num + 1
    if isprime(num) == True:
        result1.append(num)
sum = 0
for i in range (2, 2000000):
    flag = True
    for j in range (len(result1)):
        if i % result1[j] == 0 and i != result1[j]:
            flag = False
            break
    if flag == True:
        sum += i
        result2.append(i)
print(sum)
time_end = time.time()
print(time_end - time_start)


time_start2=time.time()
def isPrime(n):
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False
    return True

sum = 0
for i in range(2, 2000000):
    if isPrime(i):
        sum += i

print (sum)
time_end2=time.time()
print(time_end2 - time_start2)

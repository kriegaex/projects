def isprime(a):
    flag = True
    for i in range(2, round(a**0.5)+1):
        if a % i == 0:
            flag = False
            break
    return flag
num, count = 1, 0
while count < 2000000:
    num = num +1
    if isprime(num) == True:
        count = count + 1
print(num)

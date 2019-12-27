from uint_prime import isprime
import time
def truncatable(number):
    string = str(number)
    for i in range(1, len(string)):
        if isprime(int(string[i:])) == False:
            return False
    for i in range(1, len(string)):
        if isprime(int(string[:i])) == False:
            return False
    return True

kaishi = time.time()
start = 11
count = 0
list =[]
while count < 11:
    start += 1
    if isprime(start):
        if truncatable(start):
            count += 1
            list.append(start)
            print(count)
            print(list)
jieshu = time.time()
print(sum(list))
print(jieshu - kaishi)

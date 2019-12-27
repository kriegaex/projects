
from uint_prime import isprime
import time
start = time.time()

def pandigital(number):
    l = list(str(number))
    l.sort()
    list1 = ['1', '2', '3', '4', '5', '6', '7', '8', '9']
    if l == list1[:len(l)]:
        return True


for i in range(10000000, 2, -1):
    if pandigital(i):
      if isprime(i):
            print(i)
            break

end = time.time()
print(end - start)



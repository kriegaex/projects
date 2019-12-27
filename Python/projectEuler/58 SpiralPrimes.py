from unit_prime import isprime, isprime2
import time
# https://math.stackexchange.com/questions/1347560/quadratic-polynomials-describe-the-diagonal-lines-in-the-ulam-spiral

def ratioOfPrime():
    index = 0
    count = 0
    while True:
        index += 1
        num = 4 * (index ** 2) - 2 * index + 1
        count += 1 if isprime(num) else 0
        num = 4 * (index ** 2) + 1
        count += 1 if isprime(num) else 0
        num = 4 * (index ** 2) + 2 * index + 1
        count += 1 if isprime(num) else 0
        if count / (4 * index + 1) < 0.1:
            return 2 * index + 1

s1 = time.time()
print(ratioOfPrime())
s2 = time.time()
print(s2 - s1)
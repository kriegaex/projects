from uint_prime import isprime
import time
start = time.time()

def issquare(number):
    i = number ** 0.5
    if i % 1 == 0:
        return True

def main1():
    odd = 7
    while True:
        odd += 2
        found = False
        i = 2
        if not isprime(odd):
            while found == False:
                if isprime(i):
                    remain = (odd - i) / 2
                    if issquare(remain):
                        found = True
                        print('odd:', odd, 'prime:', i, 'square:', remain)
                i += 1
                if i > odd - 1:
                    print(odd)
                    return True

def main2():##快很多
    odd = 3
    while True:
        found = False
        odd += 2
        root = 1
        if not isprime(odd):
            while found == False:
                remainder = odd - 2 * (root ** 2)
                if isprime(remainder):
                    print('odd:', odd, 'prime:', remainder, 'square:', root ** 2)
                    found = True
                    break
                root += 1
                if (root ** 2) * 2 > odd:
                    print(odd)
                    return True

main2()
end = time.time()
print(end - start)

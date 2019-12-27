from uint_prime import isprime
import time
start = time.time()

def gererate_prime(upper_bound):
    a = []
    for i in range(2, upper_bound):
        if isprime(i):
            a.append(i)
    return a

prime_list = gererate_prime(10000)

def number_of_prime_factors(number):
    count = 0
    for i in prime_list:
        if i ** 2 > number:
            break
        if number % i == 0:
            count += 1
            while number % i == 0:
                number = number / i
    if number == 1:
        return count
    if isprime(number):
        count += 1
        return count

def main():
    start = 120
    while True:
        start += 1
        count = 0
        for i in range(start, start + 4):
            if number_of_prime_factors(i) == 4:
                count += 1
                if count == 4:
                    print(start)
                    return True

main()
end = time.time()
print(end - start)



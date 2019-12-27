from uint_prime import isprime
import time
start = time.time()

def generate_prime(lowerbound, upperbound):
    a = []
    for i in range(lowerbound, upperbound):
        if isprime(i):
            a.append(i)
    return a

prime_list = generate_prime(1000, 10001)
print(prime_list)
def ispermutation(num1, num2):
    str1, str2 = set(str(num1)), set(str(num2))
    if str1 == str2:
        return True

def main():
    for i in prime_list:
        difference = 1
        while (i + difference * 2) < 10000:
            difference += 1
            if isprime(i + difference):
                if ispermutation(i, i + difference):
                    if isprime(i + difference * 2):
                        if ispermutation(i, i + difference * 2):
                            print(i, i + difference, i + difference * 2)
main()
end = time.time()
print(end - start)

import time
from unit_prime import primes_Sieve_Eratosthenes

t1 = time.time()
prime_lis1 = [i * i for i in primes_Sieve_Eratosthenes(7071)]
prime_lis2 = [i ** 3 for i in primes_Sieve_Eratosthenes(368)]
prime_lis3 = [i ** 4 for i in primes_Sieve_Eratosthenes(84)]
lis = []

for a in prime_lis1:
    for b in prime_lis2:
        for c in prime_lis3:
            if (a + b + c) > 50000000: break
            lis.append(a + b + c)

print(len(list(set(lis))))
t2 = time.time()
print(t2 - t1)
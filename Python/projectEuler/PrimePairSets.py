from uint_prime import isprime
import time
import itertools as iter
start = time.time()

def sieve(n):
    not_prime = []
    prime = []
    for i in range(2, n+1):
        if i not in not_prime:
            prime.append(i)
            for j in range(i*i, n+1, i):
                not_prime.append(j)
    return prime

primes = sieve(10000)
set_size = 5

def make_chain(chain):
    if len(chain) == set_size:
        return chain
    for p in primes:
        if p > chain[-1] and all_prime(chain + [p]):
            new_chain = make_chain(chain + [p])
            if new_chain:
                return new_chain
    return False

def all_prime(chain):
    return all(isprime(int(str(p[0]) + str(p[1]))) for p in iter.permutations(chain, 2))

chain = 0
while not chain:
    chain = make_chain([primes.pop(0)])
print(primes.pop(0))
print(sum(map(int, chain)), chain)
end = time.time()
print(end - start)

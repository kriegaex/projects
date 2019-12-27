from unit_prime import primes_Sieve_Eratosthenes

lis_prime = primes_Sieve_Eratosthenes(100)

def primeSummations(n, prime):
    x = 2

    while True:
        lis = [0 for i in range(n)]
        lis[0] = 1

        for i in range(len(prime)):
            for j in range(prime[i], x + 1):
                lis[j] += lis[j - prime[i]]
        print(lis)
        if lis[-1] > n: break
        x += 1
    print(len(lis) - 1)

primeSummations(5000, lis_prime)

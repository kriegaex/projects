from unit_prime import primes_Sieve_Eratosthenes
import time

def check_permutation(n1, n2):
    count = [0 for i in range(10)]

    for i in str(n1):
        count[int(i)] += 1

    for i in str(n2):
        count[int(i)] -= 1

    return all(i == 0 for i in count)

s1 = time.time()
prime = primes_Sieve_Eratosthenes(10000000)

answer = {}

minimum = -1
for i in range(len(prime)):
    print(i)
    for j in range(i + 1, len(prime)):
        n = prime[i] * prime[j]
        if n > 10000000:
            break
        totient = (prime[i] - 1) * (prime[j] - 1)
        if check_permutation(n, totient):
            answer[str(n)] = 1 / ((1 - 1/prime[i]) * (1 - 1/prime[j]))
s2 = time.time()
print(s2 - s1)
print(min(answer, key=answer.get))

from unit_prime import isprime2

def totient_func(n, lis_prime):
    index = 0
    result = 1
    while index <= len(lis_prime) - 1 and lis_prime[index] <= n:
        if n % lis_prime[index] == 0:
            result *= (lis_prime[index] / lis_prime[index] - 1)
        index += 1
    return result

# it takes a long time to calculate all the number and find the maximum(brute force).
# we can use a simpler way, we can get that the more prime factor the number has, then the larger result it has
def main():
    primes = []
    # for i in range(1, 1000000):
    #     if isprime2(i):
    #         primes.append(i)
    answer = 1
    prime = 2
    while answer * prime <= 1000000:
        if isprime2(prime):
            answer *= prime
        prime += 1
    print(answer)
main()
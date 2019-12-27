from unit_prime import isprime
import time

t1 = time.time()

# lis = [i for i in range(1000001)]
# lis[1] = 0
#
# for i in range(2, 1000001):
#     print(i)
#     if isprime(i):
#         lis[i] = i - 1
#         for j in range(2, int(1000000 / i) + 1):
#             lis[i * j] *= (1 - 1 / i)
# print(sum(lis))

def P(L):
    phi = [i for i in range(L+1)]
    phi[1] = 0
    for n in range(2, L+1):
        if phi[n] == n:
            for k in range(n, L+1, n):
                phi[k] -= phi[k] // n
    return sum(phi)
print(P(1000000))
t2 = time.time()
print(t2 - t1)
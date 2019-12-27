import sys
import time

kmax = 12000
if kmax > 12:
    kmax += 1

lis = [2 * kmax] * kmax

def prodsum(product, s, c, start):
    k = product - s + c   # product - sum + number of factors
    if k < kmax:  # if k < 12000
        if product < lis[k]:
            lis[k] = product  # find the minimal Product-Sum
        for i in range(start, kmax//product * 2 + 1):
            prodsum(product * i, s + i, c + 1, i)

# prodsum(1, 1, 1, 2)
#
# print ("Answer is: ", sum(set(lis[2:])), lis[:2])


def factors(n, max_factor=sys.maxsize):
    result = []
    factor = min(n//2, max_factor)

    while factor >= 2:
        if n % factor == 0:
            divisor = n // factor
            if divisor <= factor and divisor <= max_factor:
                result.append([factor, divisor])
            result.extend([factor] + item for item in factors(divisor, factor))
        factor -= 1
    return result

def main():
    N = 12000

    F = {}
    k = [2 * i for i in range(2 * N + 1)]

    for n in range(2, 2 * N + 1):
        F[n] = factors(n)
        if F[n] != []:
            for factorization in F[n]:
                num = n - sum(factorization) + len(factorization)
                k[num] = min(k[num], n)
    print(F[212])
    print(sum(set(k[2:N + 1])))

t1 = time.time()
main()
t2 = time.time()
print("time cost: ", t2 - t1)
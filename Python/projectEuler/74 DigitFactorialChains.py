from unit_factorial import factorial
from unit_permutationOfInteger import permutation
import time

factorial_table = [factorial(i) for i in range(10)]

def next_num(n):
    next = 0
    for i in str(n):
        next += factorial_table[int(i)]
    return next

def find_chain(n):
    lis = [n]
    while True:
        next = next_num(n)
        if next in lis:
            return lis
        lis.append(next)
        n = next

def main():
    lis = [0 for i in range(1000001)]
    count = 0
    for i in range(1, 1000001):
        if lis[i] == 0:
            a = find_chain(i)

            if len(a) == 60:
                count += 1

            else:
                others = permutation(i)
                for x in others:
                    lis[x] = 1
    print(count)

t1 = time.time()
main()
t2 = time.time()
print("time spent", t2 - t1)
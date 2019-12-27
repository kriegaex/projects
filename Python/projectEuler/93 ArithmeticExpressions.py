from operator import add, sub, mul, truediv
from itertools import combinations, product, permutations

def length(s, n=1):
    while n in s:
        n += 1
    return n-1

maxt, maxint = 0, 0

for terms in combinations(range(1, 10), 4):
    s = set()
    for n in permutations(terms):
        for op in product([add, sub, mul, truediv], repeat=3):
            x = op[0](op[1](n[0], n[1]), op[2](n[2], n[3]))
            if x % 1 == 0 and x > 0:
                s.add(x)
            x = op[2](op[1](op[0](n[0], n[1]), n[2]), n[3])
            if x % 1 == 0 and x > 0:
                s.add(x)
            x = op[2](op[1](op[0](n[0], n[1]), n[2]), n[3])
            if x % 1 == 0 and x > 0:
                s.add(x)
            x = op[2](op[1](op[0](n[1], n[2]), n[0]), n[3])
            if x % 1 == 0 and x > 0:
                s.add(x)
            x = op[2](op[1](op[0](n[2], n[3]), n[1]), n[0])
            if x % 1 == 0 and x > 0:
                s.add(x)
        if length(s) > maxt:
            maxint, maxt = terms, length(s)

print(maxt, maxint)



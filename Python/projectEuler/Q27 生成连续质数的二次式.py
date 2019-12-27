def prime(n):
    for i in range(2, int(abs(n) ** 0.5) + 1):
        if n % i == 0:
            return False
            break
    return True

def formula(b, c):
    numprime, x = 0, 0
    while numprime >= 0:
        if prime(x ** 2 + b * x + c):
            numprime += 1
            x += 1
        else:
            break
    return numprime
max = 0
for c in range(1, 1001):
    if prime(c):
        for b in range(-c - 1 + 1, 1001):##prime number is positive，所以 b > - c - 1
            if formula(b, c) > max:
                max = formula(b, c)
                a = b
                d = c
        print(c)
print(max,     a,      d,       a*d )

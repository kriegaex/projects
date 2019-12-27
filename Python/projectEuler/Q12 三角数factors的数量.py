time_start = time.time()
def TriangleNumbers(n):
    a = 0.5 * (n * (n + 1))
    return a
number = 0
while number >= 0:
    factors = 0
    number += 1
    a = int(TriangleNumbers(number))
    for i in range (1, round(a ** 0.5) + 1):
        if TriangleNumbers(number) % i == 0:
                factors += 2
    if factors > 500:
        break
print(TriangleNumbers(number))


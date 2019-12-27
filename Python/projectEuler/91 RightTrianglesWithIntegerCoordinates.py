import math

total = 0
for x in range(51):
    for y in range(51):
        if x != 0 and y != 0:
            GCD = math.gcd(x, y)
            dx, dy = y / GCD, x / GCD
            total += min((50 - x) // dx, y // dy) * 2

total += 50 ** 2 * 3
print(total)

def generator(down, count):
    if count == 1:
        return down + 1, down
    else:
        count -= 1
        down = (down ** - 1) + 2
        return generator(down, count)

a, b, count = 1, 1, 0
for i in range(1, 1001):
    a, b = 2 * b + a, a + b
    if len(str(a)) > len(str(b)):
        count += 1
print(count)

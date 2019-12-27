def func(a):
    x, y, z = 0, 0, 0
    for i in range (0, a + 1):
        x = pow(i, 2) + x
    for j in range(0, a + 1):
        y = j + y
    z = pow(y, 2) - x
    return z
print(func(100))


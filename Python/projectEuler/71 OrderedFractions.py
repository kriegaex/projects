instance = []
denominator = 100000
numerator = 1

for q in range(1, 1000000):
    p = int((3 * q - 1) / 7)
    instance.append([q, p])

for group in instance:
    if group[0] * numerator < group[1] * denominator:
        denominator, numerator = group[0], group[1]
print( numerator, '/', denominator)



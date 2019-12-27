perimeter_sum = 0
x, y = 2, 1
limit = 1000000000  # 1000000000/3

while True:
    a = (2 * x - 1) / 3
    area = ((x - 2) * y) / 3

    if a > limit:
        break

    if a % 1 == 0 and a > 0 and area > 0 and area % 1 == 0:
        print(a, a - 1)
        perimeter_sum += (3 * (a) - 1)

    a = (2 * x + 1) / 3
    area = ((x + 2) * y) / 3

    if a % 1 == 0 and a > 0 and area > 0 and area % 1 == 0:
        print(a, a + 1)
        perimeter_sum += (3 * (a) + 1)

    x = 2 * x + 3 * y
    y = 2 * y + x

print(perimeter_sum)

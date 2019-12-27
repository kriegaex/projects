m = 0
while m < 32:
    m = m + 1
    for n in range (1, 32):
        a = (m ** 2) - (n ** 2)
        b = 2 * m * n
        c = m ** 2 + n ** 2
        if a + b + c == 1000:
            print (a,   b,   c,   a * b * c )

# read the article about the iteration relationship at
# https://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Continued_fraction_expansion


def OPSR(num):
    if (num ** 0.5) % 1 == 0:
        return 0

    a, m, d = [int(num ** 0.5)], [0], [1]
    n, period = 0, -1
    while True:
        m.append(d[n] * a[n] - m[n])
        d.append((num - m[n + 1] ** 2) / d[n])
        a.append(int((a[0] + m[n + 1]) / d[n + 1]))
        period += 1

        if a[n] == 2 * a[0]:
            return period

        n += 1

odd = 0
for i in range(2, 10001):
    if OPSR(i) % 2 == 1:
        odd += 1

print(odd)


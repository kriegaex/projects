def test_func(x, y, n):
    if x ** 2 - n * (y ** 2) == 1: return True
    return False

def get_pattern(num):
    a, m, d = int(num ** 0.5), 0, 1
    lis = [int(num ** 0.5)]
    while True:
        m = (d * a - m)
        d = ((num - m ** 2) / d)
        a = (int((int(num ** 0.5) + m) / d))
        lis += [a]

        if a == 2 * int(num ** 0.5):
            return lis

def get_x(lis, n):
    length = len(lis) - 1
    index = 1
    while True:
        a, b = 1, lis[index]
        for i in range(index - 1, 0, -1):
            a, b = b, a + b * lis[i]
        a = lis[0] * b + a
        if test_func(a, b, n):
            return a
        index += 1
        if index == len(lis):
            lis += lis[1:length]

def main():
    max = 0
    for i in range(2, 1001):
        if i ** 0.5 % 0.5 != 0:
            a = get_x(get_pattern(i), i)
            if a > max:
                max = a
                pos = i
    print(pos)
main()




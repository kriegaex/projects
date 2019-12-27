def summation(n):
    lis = [0 for i in range(n + 1)]
    lis[0] = 1

    for i in range(1, n):
        for j in range(i, n + 1):
            lis[j] += lis[j - i]

    print(lis)

summation(5)
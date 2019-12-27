import time

def main():
    count = 0
    M = 2
    while count <= 1000000:
        M += 1
        c = M  # a<=b<=c so c should always equal to M
        for ab in range(2, 2 * M + 1):  # ab represents (a + b)
            if ((ab * ab + c * c) ** 0.5) % 1 == 0:
                count += c - int((ab + 1)/2) + 1 if ab >= c else int(ab / 2)
    return M
t1 = time.time()
print("answer is: ", main())
t2 = time.time()
print("time cost: ", t2 - t1)



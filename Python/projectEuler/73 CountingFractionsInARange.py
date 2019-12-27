import time, math

t1 = time.time()
count = 0
for q in range(5, 12001):
    index = 0
    p = (q - 1) / 2
    while p >= (q + 1) / 3:
        if p % 1 == 0:
            if math.gcd(int(p), q) == 1:
                count += 1
        index += 1
        p = (q - 1 - index) / 2
t2 = time.time()
print("answer is:", count)
print(t2 - t1)

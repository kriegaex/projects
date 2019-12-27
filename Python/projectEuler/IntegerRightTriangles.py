tot = 0
l = 0
import time
start = time.time()
'''for p in range(120,1001):
    sum = 0
    for a in range(1, p):
        for b in range(a, p):
            c= p - a - b
            if (c <= a or c <= b):
                break
            if a * a + b * b == c * c:
                sum += 1
    if sum>tot:
        tot=sum
        l=p
print(l)
'''
for p in range(120 ,1001, 2):
    sum = 0
    for b in range(1, int(p * 0.5)):
        a = ((p ** 2) - 2 * b * p) / (2 * p - 2 * b)
        if a % 1 == 0:
            sum += 1
    if sum > tot:
        tot = sum
        l = p
print(l)
end = time.time()
print(end - start)
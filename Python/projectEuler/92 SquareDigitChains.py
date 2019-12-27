import time

t1 = time.time()
def newNum(num):
    if num == '89' or num == '1':
        return int(num)
    new = 0
    for i in num:
        new += int(i) ** 2
    return newNum(str(new))

def nextNum(num):
    new = 0
    for i in num:
        new += int(i) ** 2
    return new

lis = [0 for i in range(568)]

count = 0

for i in range(1, 568):
    lis[i] = newNum(str(i))

for i in range(1, 10000001):
    a = nextNum(str(i))
    if lis[a] == 89:
        count += 1

print(count)

t2 = time.time()
print(t2 - t1)
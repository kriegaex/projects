list = []
import time
start = time.time()
for i in range(2, 1000000):
    tester = 0
    if (sum(int(j) ** 5 for j in str(i))) == i:
        list.append(i)
    print(i)
end = time.time()
print(sum(list))
print(end - start)
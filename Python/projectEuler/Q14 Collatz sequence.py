import time
time_start = time.time()

def collatz(answer):
    count = 0
    while answer != 1:
        if answer % 2 == 0:
            answer = answer / 2
        else:
            answer = answer * 3 + 1
        count += 1
    return count
a, b = collatz(2), 0
for i in range(3, 1000000):
    if a <= collatz(i):
        a = collatz(i)
        b = i
print(a)
print(b)
time_end = time.time()
print(time_end - time_start)

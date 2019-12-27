import time
from itertools import permutations

start = time.time()

def isdivisible(string, div_list):
    for i in range(1, 8):
        a = int(string[i:i + 3])
        if a % div_list[i + 1] != 0:
            return False
    return True

sum, count = 0, 0
a = [0, 0, 2, 3, 5, 7, 11, 13, 17]
for i in permutations(['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']):
    string = "".join(i)
    if isdivisible(string, a):
        sum += int(string)
        count += 1
print(sum)

end = time.time()
print(end - start)
# we can get 10^(n-1) <= a^n < 10^n
# which is 10^((n−1)/(n)) ≤ a < 10
# since a should be integer, get the range(1, 21)   10^(20/21) = 8.9...
from math import ceil

count = 0
for i in range(1, 22):
    count += 10 - ceil(10 ** ((i - 1) / i))

print(count)


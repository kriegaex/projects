import time
start = time.time()

def ispantagon(number):
    x = ((24 * number + 1) ** 0.5 + 1) / 6
    if x % 1 == 0:
        return True

def gererate_pantagon(index):
    number = index * (index * 3 - 1) / 2
    return number

## p(n+1) - p(n) = 3n+1，所以差值会越来越大，第一个符合的差值即为最小

def main():
    for i in range(2, 3000):
        for j in range (1, i):
            plus = gererate_pantagon(i) + gererate_pantagon(j)
            if ispantagon(plus):
                minus = gererate_pantagon(i) - gererate_pantagon(j)
                if ispantagon(minus):
                    print(minus)
                    return minus
main()
end = time.time()
print(end - start)


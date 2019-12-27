import time

def pentagonal_number(n):
    lis = []
    for i in range(1, n):
        lis.append((i * (3 * i - 1)) * 0.5)
        lis.append((-i * (3 * (-i) - 1)) * 0.5)
    return lis

def count(goal):

    lis, sign = [1], [1, 1, -1, -1]
    index = 0
    pen = pentagonal_number(250)

    while lis[-1] % goal != 0:
        index += 1
        pen_pos, ins = 0, 0
        while pen[pen_pos] <= index:
            ins += lis[index - int(pen[pen_pos])] * sign[pen_pos % 4]
            pen_pos += 1
        lis.append(ins)
    return len(lis) - 1

t1 = time.time()
print(count(1000000))
t2 = time.time()
print("time spent: ", t2 - t1)
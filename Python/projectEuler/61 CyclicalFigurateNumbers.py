from math import sqrt

def test(num, idx):
    t = 0
    if idx == 3:
        t = (sqrt(1 + 8 * num) - 1) / 2
    elif idx == 4:
        t = sqrt(num)
    elif idx == 5:
        t = (sqrt(1 + 24 * num) + 1) / 6
    elif idx == 6:
        t = (sqrt(1 + 8 * num) + 1) / 4
    elif idx == 7:
        t = (sqrt(9 + 40 * num) + 3) / 10
    return t == int(t)

def partition(num, flag, pos):
    flag[pos - 3] = num

    if flag.count(0) == 0 and flag[5] // 100 == num % 100:
        print("answear:", sum(flag), flag)
        return sum(flag)
    for i in range(10, 100):
        ins = int(str(num % 100) + str(i))
        for index in range(3, 8):
            if test(ins, index) and flag[index - 3] is 0:
                # flag[index - 3] = ins
                partition(ins, flag, index)
                flag[index - 3] = 0

def main():
    result  = 0
    for i in range(19, 59):
        octagonalNum = i * ( 3 * i - 2)
        partition(octagonalNum, [0, 0, 0, 0, 0, 0], 8)

main()


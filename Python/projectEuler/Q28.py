sum = 1
startnumber = 1
def start(a, line):
    a += (line - 1)
    return a

def plus(a, line, sum):
    for i in range(0, 3):
        sum += a
        a += (line - 1)
    sum += a

    return a, sum

line = 3
while line <= 1001:
    startnumber = start(startnumber, line)
    print(startnumber)
    sum = plus(startnumber, line, sum)[1]
    instant = startnumber
    startnumber = plus(startnumber, line, instant)[0]
    line += 2
    print(sum)

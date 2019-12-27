v = [1, 2, 5, 10, 20, 50, 100, 200]
f = [[0 for i in range(205)] for j in range(10)]

for i in range(8):
    for j in range(201):
        if i == 0:
            f[i][j] = 1
            continue
        for k in range(j // v[i] + 1):
            f[i][j] += f[i - 1][j - k * v[i]]

print(f[7][200])

def p31():
    t = 200
    s = 0
    for a in range(0,3):
        t = 200 - (100*a)
        for b in range(0,t//50+1):
            t2 = t - 50*b
            for c in range(0,t2//20+1):
                t3 = t2 - 20*c
                for d in range(0,t3//10+1):
                    t4 = t3 - 10*d
                    for e in range(0,t4//5+1):
                        t5 = t4 -5*e
                        for f in range(0,t5//2+1):
                            s += 1
    return s +1

print(p31())

def nway(total, coins):
    if coins == None:
        return 0
    c, coins = coins[0], coins[1:]
    count = 0
    if total % c == 0:
        count += 1
    for amount in range( 0, total, c):
        count += nway(total - amount, coins)
    return count
# main
print(nway( 200, (1,2,5,10,20,50,100,200)))
##能写成整数之比的都是有理数
##10^k= 1(modn)!!!  (k is the length of the cycle)
##分母含2，5factor的数，出来要么是有限小数，要么是隔几位然后马上循环的小数（e.g. 1／30=0.0333333）（1位循环节）
def prime(n):
    for i in range(2, int(n ** 0.5) + 1):
        if n % i == 0:
            return False
    return True

def len(n):
    k = 1
    while k > 0:
        if (10 ** k) % n == 1:
            return k
        k += 1
maxlen = 0
for i in range(1001, 1, -1):
    if prime(i) and i!= 2 and i != 5:##为什么只看prime number就可以了？？？？？？？
        if maxlen < len(i):
            maxlen = len(i)
            a = i
    print(i)
print(maxlen)
print(a)

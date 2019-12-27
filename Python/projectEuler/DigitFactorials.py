from unit_factorial import factorial

def test(number):
    sum = 0
    order = str(number)
    for i in order:
        sum += factorial(int(i))
    if sum == number:
        return True
list = []
for i in range(3, 2540160):
    if test(i):
        list.append(i)
        print(i)


f=[1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880]
s=0
for n in range(3,100000):
  if n==sum( f[int(d)] for d in str(n) ):
    s+=n
print(s)
'''def abundant(number):
    list = [1]
    for i in range(2, int(number ** 0.5) + 1):
        if number % i == 0:
            list.append(i)
            if number / i not in list:
                list.append(number / i)
    if sum(list) > number:
        return True

array = []
for i in range(1, 28124):
    if abundant(i):
        array.append(i)
print(len(array))

list = {0}
for i in range(len(array)):
    for j in range(i, len(array)):
        list.add(array[i] + array[j])
    print(i)

list3 =[]
for i in range(1, 28123):
    if i not in list:
        list3.append(i)
print(sum(list3))'''


def y(n):
    lst = [1]
    for i in range(2, int(n ** 0.5 + 1)):
        if n % i == 0:
            lst.append(i)
            lst.append(int(n // i))
            lst = list(set(lst))
    return lst

master = [0 for z in range(28124)]
for j in range(1, 28124):
    if j == 1:
        master[j] = 1
    elif j == 2:
        master[j] = 1
    else:
        master[j] = sum(y(j))
        
abundant = []
for k in range(1, 28124):
    if k < master[k]:
        abundant.append(k)
not_abundant = [i for i in range(28124)]
for i, a in enumerate(abundant):
    for b in abundant[i:]:
        if a + b < 28124:
            not_abundant[a + b] = 0
        else:
            break  # no need to go further
print(sum(not_abundant))

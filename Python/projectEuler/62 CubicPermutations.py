num = 100
found = False
dic = {}

while found is not True:
    ins_lis = list(str(num ** 3))
    ins_lis.sort()
    key = ''.join(ins_lis)

    if dic.get(key) is None:
        dic[key] = [1, num ** 3]
    else:
        dic[key][0] += 1
        if dic[key][0] is 5:
            found = True
            print(dic[key][1])
    num += 1


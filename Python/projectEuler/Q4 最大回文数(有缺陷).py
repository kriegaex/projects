list=[]
for i in range (100, 999):
    for j in range (100, 999):
        a=str(i * j)
        for z in range (3):
            if a[z] != a[len(a) - z - 1]:
                break
        else:
            list.append(int(a))
            print(a)



def largest_index(a):
    x = 0
    for i in range(len(a) - 1):
        if a[x] < a[i + 1]:
            x = i + 1
    print(a[x])
largest_index(list)
#run on PC charm will use shorter time

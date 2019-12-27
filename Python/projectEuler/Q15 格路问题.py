a = int(input('How mant rows do u want: '))
b = int(input('How mant columns do u want: '))

def up(NumberOfRow, NumberOfColumn):
    product = 1
    for i in range(1, NumberOfRow + NumberOfColumn + 1):
        product = product * i
    return product

def down(NumberOfRow, NumberOfColumn):
    product1,product2 = 1, 1
    for i in range(1, NumberOfRow + 1):
        product1 = product1 * i
    for i in range(1, NumberOfColumn + 1):
        product2 = product2 * i
    return product1 * product2

print(up(a, b) / down(a, b))

##所有的路径都必须经过m个横和n个竖，所以就是把m个-和n个|排成一列求有多少种方法，即（m+n）！。
##再除去重复的部分即为答案

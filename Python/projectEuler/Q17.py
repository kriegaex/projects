a = [0,3,3,5,4,4,3,5,5,4]
b = [0,0,6,6,5,5,5,7,6,6]
c = [3,6,6,8,8,7,7,9,8,8]
def main():
    sum = 0
    for i in range(0, 10):
        sum += a[i]
    for i in range(10, 20):
        sum += c[i % 10]
        print(sum)
    for i in range(20, 100):
        sum += b[int(str(i)[0])] + a[i % 10]
    for i in range(100,1000):
        if int(str(i)[1]) == 1 :
            sum += c[i % 10] + a[int(str(i)[0])] + 10
        else:
            if i % 100 != 00:
                sum += a[int(str(i)[0])] + b[int(str(i)[1])] + a[i % 10] + 10
            else:
                sum += a[int(str(i)[0])] + 7
    sum += len('onethousand')
    print(sum)
main()

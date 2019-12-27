i = 2520
for k in (range(11, 20)):
    if i % k > 0:
        for j in range(1, 21):
            if (i*j) % k == 0:
                i = j * i   #不停的乘，知道找到是倍数为止，然后回到上一个循环接着找下一个数的倍数
                break
print (i)

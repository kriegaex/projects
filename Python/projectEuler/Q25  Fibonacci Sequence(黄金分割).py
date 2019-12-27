a, b , index = 1, 1, 2
length = len(str(b))
while length < 1000:
    temp = a + b
    a = b
    b = temp
    index += 1
    length = len(str(b))

print(index)

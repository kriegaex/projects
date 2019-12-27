index = 2
lis_index = []

for i in range(1, 101):
    lis_index.append(1)
    lis_index.append(i * index)
    lis_index.append(1)
lis_index = lis_index[0:99]

a = 1
b = lis_index[-1]
count = 0
for i in range(len(lis_index) - 2, -1, -1):
    count += 1
    a, b = b, lis_index[i] * b + a
sum = 0
for i in str(a + 2 * b):
    sum += int(i)
print(sum)

nums = []
foo = open('p079_keylog.txt', 'r')
for i in foo:
    nums.append(int(i))
ins = list(set(nums))

lis = [[0, 0, 0, i] for i in range(10)]
for i in ins:
    a = str(i)
    for pos in range(len(a)):
        lis[int(a[pos])][pos] += 1
print(lis)


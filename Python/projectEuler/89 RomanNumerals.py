def cal_space(lis):
    total_space_before = 0
    for i in lis:
        total_space_before += len(i)
    return total_space_before

fo = open('p089_roman.txt', 'r')

nums = []
for i in fo:
    nums.append(i.strip('\n'))

t1 = cal_space(nums)

for i in range(len(nums)):
    nums[i] = nums[i].replace('VIIII', 'IX')
    nums[i] = nums[i].replace('IIII', 'IV')
    nums[i] = nums[i].replace('LXXXX', 'XC')
    nums[i] = nums[i].replace('XXXX', 'XL')
    nums[i] = nums[i].replace('DCCCC', 'CM')
    nums[i] = nums[i].replace('CCCC', 'CM')

t2 = cal_space(nums)

print(t1 - t2)


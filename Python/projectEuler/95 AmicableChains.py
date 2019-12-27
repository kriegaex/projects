from unit_factorization import factors
import time
t1 = time.time()
# def next(num):
#     return 1 + sum(map(sum, factors(num)))
#
#
# lis = [0 for i in range(1000001)]
# max_len = 1
#
# for i in range(2, 1000001):
#
#     print('i', i)
#     next_num = next(i)
#     lis2 = [i]
#     if lis[i] == 0:
#         while next_num != i and next_num != 1 and lis[next_num] == 0:
#                 #print(next_num)
#                 lis2.append(next_num)
#                 lis[next_num] = 1
#                 next_num = next(next_num)
#                 #print('list2', lis2)
#                 if next_num > 1000000:
#                     break
#
#     if len(lis2) > max_len and lis2[0] == lis2[-1]:
#         max_len = len(lis2)
#         max_list = lis2
#         print('max_list', max_list)
#
# print('max_length', max_len)

def pe95(L = 1000000):
    d = [1] * L
    for i in range(2, L//2):
        for j in range(2*i, L, i):
            d[j] += i

    max_cl = 0
    for i in range(2, L):
        n, chain = i, []
        while d[n] < L:
            d[n], n = L+1, d[n]  # d[n] = L + 1是为了以后再到这个数就不用计算了
            try: k = chain.index(n) # 当重复的数出现（形成环）
            except ValueError: chain.append(n) # 如果chain里面没有就append进去
            else:
                if len(chain[k:]) > max_cl:
                    max_cl, min_link = len(chain[k:]), min(chain[k:])
    return min_link

print ("Smallest member of the longest amicable chain", pe95())
t2 = time.time()
print(t2 - t1)

import itertools
counter = 0
for i in list(itertools.permutations([0,1,2,3,4,5,6,7,8,9])):
    counter += 1
    if counter == 1000000:
        print(i)
        break

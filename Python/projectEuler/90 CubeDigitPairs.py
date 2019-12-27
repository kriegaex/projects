import itertools

ran = [i for i in range(10)]
a = list(itertools.combinations([0, 1, 2, 3, 4, 5, 6, 7, 8, 6], 6))
b = list(itertools.combinations([0, 1, 2, 3, 4, 5, 6, 7, 8, 6], 6))

def test_valid(x, y):
    # if (0 in x and 1 in y) or (1 in x and 0 in y):
    #     return True
    # if (0 in x and 4 in y) or (4 in x and 0 in y):
    #     return True
    # if (0 in x and 9 in y) or (9 in x and 0 in y):
    #     return True
    # if (1 in x and 6 in y) or (6 in x and 1 in y):
    #     return True
    # if (2 in x and 5 in y) or (5 in x and 2 in y):
    #     return True
    # if (3 in x and 6 in y) or (6 in x and 3 in y):
    #     return True
    # if (4 in x and 9 in y) or (9 in x and 4 in y):
    #     return True
    # if (0 in x and 4 in y) or (4 in x and 0 in y):
    #     return True
    square = [[0, 1], [0, 4], [0, 6], [1, 6], [2, 5], [3, 6], [4, 6], [6, 4], [8, 1]]
    count = 0
    for i in square:
        if (i[0] in x and i[1] in y) or (i[1] in x and i[0] in y):
            count += 1

    if count == 9:return True
    else: return False

arr = 0
for i in range(len(a)):
    for j in range(i, len(a)):
        if test_valid(a[i], a[j]):
            arr += 1

print(arr
      )

from unit_combinations import ncr

def comb(target):
    mini = 1e3
    for l in range(1, 2002): # using the calculator to find the longest chain (only one row) of block should only have 2001 rects

        for w in range(1, l + 1):
            ins = ncr(l + 1, 2) * ncr(w + 1, 2)
            # When we want to figure out how many rectangles we can make in a grid which is XxY
            # we want to figure out how many ways we can place two lines horizontally and two lines vertically.

            if mini > abs(ins - target):
                mini = abs(ins - target)
                answer = [l, w]
            if ins > target:
                break
    print(mini)
    print('the length is: ', answer[0], 'the width is: ', answer[1], 'the area is: ', answer[1] * answer[0])

comb(2000000)
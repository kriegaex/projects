import time

foo = open('p081_matrix.txt', 'r')

matrix = [line.split(',') for line in foo.readlines()]
foo.close()
#matrix = [[int(i) for i in j] for j in matrix]
matrix = [list(map(int, i)) for i in matrix]

dim = len(matrix)

result = [matrix[i][dim - 1] for i in range(dim)]

#for row in range(dim):
#     for column in range(dim - 2, 0, -1):
#         if row == 0: matrix[row][column] += matrix[row][column + 1]
#         else: matrix[row][column] += min(matrix[row - 1][column], matrix[row][column - 1])
#
# for row in range(dim - 1, 0, -1):
#     for column in range(dim - 1, -1):
#         matrix[row][column] = min(matrix[row + 1][column], matrix[row][column])

for column in range(dim - 2, -1, -1):
    result[0] += matrix[0][column]
    for row in range(1, dim):
        result[row] = min(result[row] + matrix[row][column], result[row - 1] + matrix[row][column])

    for row in range(dim - 2, -1, -1):
        result[row] = min(result[row], matrix[row][column] + result[row + 1])

print(min(result))

import time

t1 = time.time()

foo = open('p081_matrix.txt', 'r')

matrix = [line.split(',') for line in foo.readlines()]
foo.close()
#matrix = [[int(i) for i in j] for j in matrix]
matrix = [list(map(int, i)) for i in matrix]

dimension = len(matrix)

for i in range(dimension):
    for j in range(dimension):
        if i != 0 or j != 0:
            if i == 0: matrix[i][j] += matrix[i][j - 1]
            elif j == 0: matrix[i][j] += matrix[i - 1][j]
            else: matrix[i][j] += min(matrix[i - 1][j], matrix[i][j - 1])

print(matrix[dimension - 1][dimension - 1])
t2 = time.time()
print('time taken: ', t2 - t1)


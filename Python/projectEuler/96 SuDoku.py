fo = open('p096_sudoku.txt', 'r')

sudoku = []
for line in fo:
    sudoku.append(line.strip('\n'))

sudoku = [[i for i in j] for j in sudoku]
for i in range(9):
    del sudoku[i * 10 - i]

def check_col(grid, col, obj, index):
    start = 9 * (index - 1) + 1
    for i in range(start, start + 9):
        if obj == grid[i][col]:
            return False
    return True

def check_row(grid, row, obj, index):
    start = 9 * (index - 1) + row
    for i in range(9):
        if obj == grid[start][i]:
            return False
    return True

a = ['1', '2', '3', '4', '5', '6', '7', '8', '9']

for indexSudoku in range(1, 10):
    for row in range(9):
        for column in range(9):
            if sudoku[indexSudoku * 9 + row][column] == 0:
                while check_col(sudoku, column, a):



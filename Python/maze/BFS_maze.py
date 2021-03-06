from unit_queue import Queue
import turtle
from random import randint
from time import sleep

# Create a search list for positions yet to explore, add the entrance to this Queue
# While the Queue is not empty
#      Remove the next position from the search list
#      If it is the exit position, [n-1, n-1],
#           then a path is found return True.
#      Otherwise, mark the position as visited, add all valid up, right, down, or left neighbor  positions to the search list (in that order)
# If the list is empty and the method has not returned, there is no path


screen = turtle.Screen()
myPen = turtle.Turtle()
screen.tracer(0)
myPen.speed(50)
myPen.hideturtle()


def text(message, x, y, size):
    FONT = ('Arial', size, 'normal')
    myPen.penup()
    myPen.goto(x, y)
    myPen.write(message, align="left", font=FONT)


# This function draws a box by drawing each side of the square and using the fill function
def box(intDim):
    myPen.begin_fill()
    # 0 deg.
    myPen.forward(intDim)
    myPen.left(90)
    # 90 deg.
    myPen.forward(intDim)
    myPen.left(90)
    # 180 deg.
    myPen.forward(intDim)
    myPen.left(90)
    # 270 deg.
    myPen.forward(intDim)
    myPen.end_fill()
    myPen.setheading(0)


##Here is an example of how to draw a box
# box(boxSize)

##Here are some instructions on how to move "myPen" around before drawing a box.
# myPen.setheading(0) #point to the right, 90 to go up, 180 to go to the left 270 to go down
# myPen.penup()
# myPen.forward(boxSize)
# myPen.pendown()

# Here is how your PixelArt is stored (using a "list of lists")
palette = ["#FFFFFF", "#000000", "#00ff00", "#ff00ff", "#AAAAAA"]
#colour code: Hexadecimal Colour Codes(#FFFFFF represents for white while #000000 represents black)

def create_maze(n):
    maze = [[1] * n]
    for row in range(n - 2):
        row = [1]
        for col in range(n - 2):
            if randint(0, 3) == 0:
                row.append(1)
            else:
                row.append(0)
        row.append(1)
        maze.append(row)
    maze.append([1] * n)

    maze[0][1] = 0
    maze[n - 2][n - 1] = 2
    return maze

# def create_maze1(n):
#     maze_rand = [[1 for i in range(n)]]
#     for i in range (1, n - 1):
#         row = [1]
#         for j in range(n - 2):
#             row.append(randint(0, 1))
#         row.append(1)
#         maze_rand.append(row)
#     maze_rand.append([1 for i in range(n)])
#     maze_rand[n - 2][n - 1] = 2
#     maze_rand[0][1] = 0
#     return maze_rand



def print_maze(maze):
    # system('cls')
    for row in maze:
        print((row))
    # sleep(0.8)


def drawMaze(maze):
    boxSize = 13  # the size of one black box

    # Position myPen in top left area of the screen
    myPen.penup()
    myPen.goto(-130, 130)
    myPen.setheading(0)
    for i in range(0, len(maze)):
        for j in range(0, len(maze[i])):
            myPen.color(palette[maze[i][j]])
            box(boxSize)
            myPen.penup()
            myPen.forward(boxSize)
            myPen.pendown()
        myPen.setheading(270)
        myPen.penup()
        myPen.forward(boxSize)
        myPen.setheading(180)
        myPen.forward(boxSize * len(maze[i]))
        myPen.setheading(0)
        myPen.pendown()


def is_valid_pos(tup):
    print('checking {}'.format(tup))
    (row, col) = tup
    if col < 0 or row < 0 or col >= MAZE_SIZE or row >= MAZE_SIZE:
        return False
    return maze[row][col] == 0 or maze[row][col] == 2  #if first condition is false then it tries the second one


# A backtracking/recursive function to check all possible paths until the exit is found
def exploreMaze(maze, row, col):
    q = Queue()
    # text('pushing ({},{})'.format(row, col), -300, -170, 20)
    q.enqueue((row, col))

    while not q.isEmpty():  # when the Queue is empty, it means there is no way to go

        # the position of the words(the left downside ones)
        text('Queue contents: {}'.format(q.items), -300, -200, 20)
        # input('Press Enter to continue: ')
        text('Popping Queue', -300, -230, 20)
        (row, col) = q.dequeue()
        # give the current position
        text('Current position: ({}, {})'.format(row, col), -300, -260, 20)
        #sleep(1)

        if maze[row][col] == 2:
            # We found the exit
            return True
        elif maze[row][col] == 0:  # Empty path, not explored
            maze[row][col] = 3   # show the path that the box has walked through
            myPen.clear()
            drawMaze(maze)
            myPen.getscreen().update()    #what is the funciton of this part? it has no influence to the maze without this

            # As we are using a Queue, the order of searching is reversed compared to the recursive version
            if is_valid_pos((row, col + 1)): q.enqueue((row, col + 1))
            if is_valid_pos((row + 1, col)): q.enqueue((row + 1, col))
            if is_valid_pos((row, col - 1)): q.enqueue((row, col - 1))
            if is_valid_pos((row - 1, col)): q.enqueue((row - 1, col))

            maze[row][col] = 4 # show the moving object, 4 represents the colour pink
            myPen.clear()
            drawMaze(maze)
            myPen.getscreen().update()



while True:
    myPen.clear()
    maze = create_maze(20)
    # print_maze(maze)
    drawMaze(maze)
    myPen.getscreen().update()
    MAZE_SIZE = len(maze)

    solved = exploreMaze(maze, 0, 1)
    if solved:
        print("Maze Solved")
        text("Maze Solved", -100, -150, 20)
        sleep(1)
    else:
        print("Cannot Solve Maze")
        text("Cannot Solve Maze", -130, -150, 20)
        sleep(1)

    myPen.getscreen().update()
turtle.done()


# keep going, if there is different options, record the coordinates in the Queue.
# The current coordinates will be deleted each time move the box, and if next position is valid, it push that position into
# the Queue.
# until the Queue is empty or reaches the exit

#question: which line of program puts the box on the last position in the Queue
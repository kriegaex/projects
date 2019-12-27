import networkx as nx
import numpy as np
from heapq import *
# foo = open('p081_matrix.txt', 'r')

# matrix = [line.split(',') for line in foo.readlines()]
# matrix = [list(map(int, i)) for i in matrix]

matrix = np.loadtxt('p081_matrix.txt', dtype=int, delimiter=',')

def networkx_method(matrix):
    n = m = len(matrix)

    G = nx.DiGraph()
    for i in range(n):
        for j in range(m):
            neighbors = [(i + x, j + y) for x, y in [(-1, 0), (0, -1), (1, 0), (0, 1)] if
                         0 <= i + x < n and 0 <= j + y < m]
            for ix, jy in neighbors:
                G.add_edge((i, j), (ix, jy), weight=matrix[ix][jy])

    path_length = nx.dijkstra_path_length(G, source=(0, 0), target=(n - 1, m - 1))

    print("Minimum path sum in", n, "by", m, "matrix =", path_length + matrix[0][0])

def simplified_dijkstra(matrix):
    ln = len(matrix)

    dist = [[int(1e8) for c in range(ln)] for r in range(ln)]
    dist[0][0] = matrix[0][0]
    todo = [(0, 0)]
    while todo:
        (r, c) = todo.pop(0)  # use the first point of the todo list

        for n, b in [(-1, 0), (0, -1), (1, 0), (0, 1)]:  # 4 neighbours
            nr, nc = r + n, c + b
            # valid node?
            if not (0 <= nr < ln and 0 <= nc < ln):
                continue
            d = dist[r][c] + matrix[nr][nc]
            if dist[nr][nc] > d:
                dist[nr][nc] = d
                todo.append((nr, nc))
    print(dist[-1][-1])

def AStar_search(matrix):
    INFINITY = int(1e8)
    size = len(matrix)

    visited = [[False for i in range(size)] for j in range(size)]
    distances = [[INFINITY for i in range(size)] for j in range(size)]
    distances[0][0] = matrix[0][0]
    queue = [(0, 0)]
    n = find_min(matrix)

    while len(queue) > 0:
        i, j = min(queue, key=lambda v: distances[v[0]][v[1]] + heuristic(n, v, matrix))
        # lambda here take each pair of numbers in the queue as v and return
        # corresponding weight in the 'distance' list
        queue.remove((i, j)) # which means
        visited[i][j] = True

        for ni, nj in [(i + 1, j), (i - 1, j), (i, j + 1), (i, j - 1)]:
            if 0 <= ni < size and 0 <= nj < size and not visited[ni][nj]:
                distances[ni][nj] = min(distances[ni][nj], distances[i][j] + matrix[ni][nj])

                if (ni, nj) not in queue:
                    queue.append((ni, nj))

    print(distances[-1][-1])

def heuristic(n, pos, matrix):
    return n * 2 * (len(matrix) - 1) - pos[1] - pos[0]

def find_min(matrix):
    mini = int(1e8)
    for i in matrix:
        for j in i:
            if j < mini:
                mini = j
    return mini

def dijkstra(M):
    h, w = len(M), len(M[0])

    Q = []
    heappush(Q, (0, 0, 0))
    visited = set()
    while Q:
        Σ, x, y = heappop(Q)
        Σ += M[x][y]

        if x == w - 1 and y == h - 1:
            return Σ

        if (x, y) in visited:
            continue
        visited.add((x, y))

        for dx, dy in [(1, 0), (-1, 0), (0, 1), (0, -1)]:
            if 0 <= x + dx < w and 0 <= y + dy < h:
                heappush(Q, (Σ, x + dx, y + dy))
    return -1
print(dijkstra(matrix))
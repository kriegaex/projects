from random import randint, shuffle
import queue

def result_dices():
    dices = [randint(1, 6), randint(1, 6)]
    return dices

def CC(card):
    if card == 0:
        return 0
    elif card == 1:
        return 10
    else: return False

def Chance(card, current_pos):

    if 0 <= card <= 5:
        card_lis = [0, 10, 11, 24, 39, 5]
        return card_lis[card]

    elif card == 6 or card == 7:
        if current_pos == 7: return 15
        elif current_pos == 22: return 25
        elif current_pos == 36: return 5

    elif card == 8:
        if 12 <= current_pos <= 27: return 28
        else: return 12

    elif card == 9: return -3
    else: return False

def main():
    a = [i for i in range(16)]
    shuffle(a)
    chance_queue, cc_queue = queue.Queue(), queue.Queue()
    for i in a:
        chance_queue.put(i)

    b = [i for i in range(16)]
    shuffle(b)
    for i in b:
        cc_queue.put(i)

    visited = [0 for i in range(40)]
    double = 0
    current = 0
    for i in range(1000000):
        dices = result_dices()
        if dices[0] == dices[1]:
            double += 1
        else:
            double = 0

        if double == 3:
            current = 10
            visited[10] += 1
            double = 0
            continue

        if current + sum(dices) < 40: current += sum(dices)
        else: current = (current + sum(dices)) - 40

        if current in [7, 22, 36]:
            chance_card = chance_queue.get()
            chance_queue.put(chance_card)
            ins = Chance(chance_card, current)

            if ins != False:
                if ins >= 0: current = ins
                else:
                    if current - 3 >= 0: current -= 3
                    else: current = current - 3 + 40

        elif current in [2, 17, 33]:
            a = cc_queue.get()
            cc_queue.put(a)
            ins = CC(a)
            if ins != False:
                current = ins

        if current == 30:
            current = 10

        visited[current] += 1
    for i in range(3):
        b = visited.index(max(visited))
        print(b)
        visited[b] = 0
    print(visited)
main()
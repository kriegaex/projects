import time

def remove_repeat1(sea, needle):
    sea.sort()
    upper_boundary = len(sea)
    lower_boundary = 0
    while True:
        if upper_boundary == lower_boundary:
            return True

        mid_index = (lower_boundary + upper_boundary) // 2
        mid_number = sea[mid_index]

        if mid_number == needle:
            return False

        if mid_number < needle:
            lower_boundary = mid_index + 1
        if mid_number > needle:
            upper_boundary = mid_index

def remove_repeat2(sea, needle):
    for i in range(len(sea)):
        if sea[i] == needle:
            return False
    return True

def use_sort():
    print(len(set(a ** b for a in range(2, 101) for b in range(2, 101))))

start = time.time()

def main():
    list = []
    for i in range(2, 101):
        for j in range(2, 101):
            if remove_repeat1(list, i ** j):
                list.append(i ** j)

    print(len(list))

use_sort()
end = time.time()
print(end - start)
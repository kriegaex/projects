def equal_digit(sort1, num2):
    list2 = list(str(num2))
    sort2 = sorted(list2)
    if sort1 == sort2:
        return True


def main():
    index = 100
    while True:
        list1 = list(str(index))
        sort1 = sorted(list1)
        count = 0
        for i in range(2, 7):
            if not equal_digit(sort1, i * index):
                break
            else:
                 count += 1
        if count != 5:
            index += 1
        else:
            print(index)
            break

main()